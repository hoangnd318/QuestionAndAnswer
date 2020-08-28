/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.question;

import dao.answer.AnswerConnection;
import dao.dbconnection.DBConnection;
import dao.question_follow.QuestionFollowConnection;
import dao.question_tag.QuestionTagConnection;
import dao.question_vote.QuestionVoteConnection;
import dao.tag.TagConnection;
import dao.user.UserConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Question;
import model.QuestionFollow;
import org.apache.commons.lang.StringUtils;
import service.ConvertSEO;
import service.ConvertTimestamp;
import service.TFIDF;

/**
 *
 * @author nguye
 */
public class QuestionQueryFacade {

    //tim kiem
    public static int FIND_BY_TIME = 1;
    public static int FIND_BY_KEY = 2;
    public static int FIND_BY_ID = 4;
    public static int FIND_ALL_NO_ANSWER = 5;
    public static int FIND_BY_ID_AND_RAW_TITLE = 6;
    public static int FIND_BY_COMMENT_ID = 7;
    public static int FIND_BY_USER_ID_SORT_NEW = 8;
    public static int FIND_BY_TAG_NAME = 9;
    public static int FIND_BY_USER_ID_SORT_OLD = 10;
    public static int FIND_ALL_NO_ANSWER_SORT_OLD = 11;
    public static int FIND_ALL_NO_ANSWER_SORT_VOTE = 12;
    public static int FIND_BY_TAG_NAME_SORT_OLD = 13;
    public static int FIND_BY_TAG_NAME_SORT_VOTE = 14;
    public static int FIND_BY_KEY_SORT_OLD = 15;
    public static int FIND_BY_KEY_SORT_VOTE = 16;
    public static int FIND_ALL_TF_IDF = 17;
    public static int FIND_RECOMMENDED = 18;

    //do dai
    public static int COUNT_ALL_QUESTION = 1;
    public static int COUNT_BY_USER_ID = 2;
    public static int COUNT_SEARCH_QUESTION = 3;
    public static int COUNT_SEARCH_QUESTION_BY_TAG = 4;
    public static int COUNT_FIND_BY_USER_ID = 5;

    //them sua xoa
    public static int ADD_NEW_QUESTION = 1;
    public static int EDIT_QUESTION = 2;
    public static int UPDATE_VIEW = 3;
    public static int DELETE_BY_QUESTION_ID = 4;

    private PreparedStatement preparedStatement;

    public QuestionQueryFacade() {
        this.preparedStatement = null;
    }

    public ArrayList<Question> find(int type, ArrayList<Object> key, int user_id) {
        ArrayList<Question> questions = new ArrayList<>();
        String sql_query = "";
        if (type == QuestionQueryFacade.FIND_BY_ID) {
            sql_query = "SELECT * FROM " + Question.TABLE_NAME + " WHERE " + Question.COLUMN_ID + " = ?";
        } else if (type == QuestionQueryFacade.FIND_ALL_TF_IDF) {
            sql_query = "SELECT * FROM " + Question.TABLE_NAME;
        } else if (type == QuestionQueryFacade.FIND_BY_TIME) {
            sql_query = "SELECT * FROM " + Question.TABLE_NAME + " ORDER BY " + Question.COLUMN_CREATE_TIME + " DESC LIMIT 0, 5 ";
        } else if (type == QuestionQueryFacade.FIND_BY_KEY) {
            sql_query = "SELECT *, MATCH (a.title, a.content) AGAINST (?) as score "
                    + "FROM question a "
                    + "WHERE MATCH (a.title,a.content) AGAINST (?) > 0 "
                    + "ORDER BY score DESC, a.create_time DESC "
                    + "LIMIT ?, ?";
        } else if (type == QuestionQueryFacade.FIND_BY_KEY_SORT_OLD) {
            sql_query = "SELECT *, MATCH (a.title, a.content) AGAINST (?) as score "
                    + "FROM question a "
                    + "WHERE MATCH (a.title,a.content) AGAINST (?) > 0 "
                    + "ORDER BY score DESC, a.create_time ASC "
                    + "LIMIT ?, ?";
        } else if (type == QuestionQueryFacade.FIND_BY_KEY_SORT_VOTE) {
            sql_query = "SELECT *,"
                    + "((SELECT COUNT(*) total FROM question_vote b WHERE b.question_id = a.id&&b.type='vote_up')-(SELECT COUNT(*) total FROM question_vote b WHERE b.question_id = a.id&&b.type='vote_down')) AS vote,"
                    + "(SELECT COUNT(*) FROM answer c WHERE c.question_id=a.id) AS ans, "
                    + "MATCH (a.title,a.content) AGAINST (?) as score "
                    + "FROM question a "
                    + "WHERE MATCH (a.title,a.content) AGAINST (?) > 0 "
                    + "ORDER BY score DESC,vote DESC,ans DESC "
                    + "LIMIT ?,?";
        } else if (type == QuestionQueryFacade.FIND_ALL_NO_ANSWER) {
            sql_query = "SELECT * FROM "
                    + Question.TABLE_NAME
                    + " ORDER BY "
                    + Question.COLUMN_CREATE_TIME
                    + " DESC LIMIT ?, ? ";
        } else if (type == QuestionQueryFacade.FIND_ALL_NO_ANSWER_SORT_OLD) {
            sql_query = "SELECT * FROM "
                    + Question.TABLE_NAME
                    + " ORDER BY "
                    + Question.COLUMN_CREATE_TIME
                    + " ASC LIMIT ?, ? ";
        } else if (type == QuestionQueryFacade.FIND_ALL_NO_ANSWER_SORT_VOTE) {
            sql_query = "SELECT *,((SELECT COUNT(*) total FROM question_vote b WHERE b.question_id = a.id&&b.type='vote_up')-(SELECT COUNT(*) total FROM question_vote b WHERE b.question_id = a.id&&b.type='vote_down')) AS vote,"
                    + "(SELECT COUNT(*) FROM answer c WHERE c.question_id=a.id) AS ans "
                    + "FROM question a "
                    + "ORDER BY vote DESC, "
                    + "ans DESC "
                    + "LIMIT ?, ?";
        } else if (type == QuestionQueryFacade.FIND_BY_ID_AND_RAW_TITLE) {
            sql_query = "SELECT * FROM "
                    + Question.TABLE_NAME
                    + " WHERE "
                    + Question.COLUMN_ID + " = ? && "
                    + Question.COLUMN_TITLE_RAW + " = ?";
        } else if (type == QuestionQueryFacade.FIND_BY_COMMENT_ID) {
            sql_query = "SELECT a.id,"
                    + "a.title,"
                    + "a.content,"
                    + "a.create_time,"
                    + "a.user_id,"
                    + "a.point_priority,"
                    + "a.view,"
                    + "a.title_raw,"
                    + "a.content_raw "
                    + "FROM question a"
                    + " INNER JOIN answer b ON b.question_id=a.id"
                    + " INNER JOIN comment c ON c.answer_id=b.id"
                    + " WHERE c.id = ?";
        } else if (type == QuestionQueryFacade.FIND_BY_USER_ID_SORT_NEW) {
            sql_query = "SELECT b.id,"
                    + "b.title,"
                    + "b.content,"
                    + "b.create_time,"
                    + "b.user_id,"
                    + "b.point_priority,"
                    + "b.view,"
                    + "b.title_raw,"
                    + "b.content_raw "
                    + "FROM user a"
                    + " INNER JOIN question b ON b.user_id = a.id_user"
                    + " WHERE a.id_user = ?"
                    + " ORDER BY b.create_time DESC"
                    + " LIMIT ?,?";
        } else if (type == QuestionQueryFacade.FIND_BY_USER_ID_SORT_OLD) {
            sql_query = "SELECT b.id,"
                    + "b.title,"
                    + "b.content,"
                    + "b.create_time,"
                    + "b.user_id,"
                    + "b.point_priority,"
                    + "b.view,"
                    + "b.title_raw,"
                    + "b.content_raw "
                    + "FROM user a"
                    + " INNER JOIN question b ON b.user_id = a.id_user"
                    + " WHERE a.id_user = ?"
                    + " ORDER BY b.create_time ASC"
                    + " LIMIT ?,?";
        } else if (type == QuestionQueryFacade.FIND_BY_TAG_NAME) {
            sql_query = "SELECT a.id,a.title,a.content,a.create_time,a.user_id,a.point_priority,a.view,a.title_raw,a.content_raw "
                    + "FROM question a "
                    + "INNER JOIN question_tag b "
                    + "ON b.question_id=a.id "
                    + "INNER JOIN tag c "
                    + "ON c.id=b.tag_id "
                    + "WHERE c.name=?"
                    + " ORDER BY a.create_time DESC "
                    + "LIMIT ?,?";
        } else if (type == QuestionQueryFacade.FIND_BY_TAG_NAME_SORT_OLD) {
            sql_query = "SELECT a.id,a.title,a.content,a.create_time,a.user_id,a.point_priority,a.view,a.title_raw,a.content_raw "
                    + "FROM question a "
                    + "INNER JOIN question_tag b "
                    + "ON b.question_id=a.id "
                    + "INNER JOIN tag c "
                    + "ON c.id=b.tag_id "
                    + "WHERE c.name=?"
                    + " ORDER BY a.create_time ASC "
                    + "LIMIT ?,?";
        } else if (type == QuestionQueryFacade.FIND_BY_TAG_NAME_SORT_VOTE) {
            sql_query = "SELECT a.id,a.title,a.content,a.create_time,a.user_id,a.point_priority,a.view,a.title_raw,a.content_raw,"
                    + "((SELECT COUNT(*) total FROM question_vote b WHERE b.question_id = a.id&&b.type='vote_up')-(SELECT COUNT(*) total FROM question_vote b WHERE b.question_id = a.id&&b.type='vote_down')) AS vote,"
                    + "(SELECT COUNT(*) FROM answer c WHERE c.question_id=a.id) AS ans "
                    + "FROM question a "
                    + "INNER JOIN question_tag d "
                    + "ON d.question_id=a.id "
                    + "INNER JOIN tag e "
                    + "ON e.id=d.tag_id "
                    + "WHERE e.name=? "
                    + "ORDER BY vote DESC, ans DESC "
                    + "LIMIT ?,?";
        } else if (type == QuestionQueryFacade.FIND_RECOMMENDED) {
            if (key.size() <= 1) {
                sql_query = "SELECT DISTINCT a.id,a.title,a.content,a.create_time,a.user_id,a.point_priority,a.view,a.title_raw,a.content_raw "
                        + "FROM question a "
                        + "INNER JOIN question_tag b "
                        + "ON B.question_id=a.id "
                        + "INNER JOIN tag c "
                        + "ON c.id=b.tag_id "
                        + "WHERE c.name=?";
            } else {
                sql_query = "SELECT DISTINCT a.id,a.title,a.content,a.create_time,a.user_id,a.point_priority,a.view,a.title_raw,a.content_raw "
                        + "FROM question a "
                        + "INNER JOIN question_tag b "
                        + "ON B.question_id=a.id "
                        + "INNER JOIN tag c "
                        + "ON c.id=b.tag_id "
                        + "WHERE "
                        + "c.name=?";
                for (int i = 0; i < key.size() - 1; i++) {
                    sql_query += " || c.name=?";
                }
            }
        }
        try {
            preparedStatement = DBConnection.getInstance().getConnection().prepareStatement(sql_query);
            if (type == QuestionQueryFacade.FIND_BY_ID) {
                int id = (int) key.get(0);
                preparedStatement.setInt(1, id);
            } else if (type == QuestionQueryFacade.FIND_BY_KEY
                    || type == QuestionQueryFacade.FIND_BY_KEY_SORT_OLD
                    || type == QuestionQueryFacade.FIND_BY_KEY_SORT_VOTE) {
                String keySearch = (String) key.get(0);
                int page = (int) key.get(1);
                int endPage = 10 * page;
                int startPage = endPage - 10;
                preparedStatement.setString(1, keySearch);
                preparedStatement.setString(2, keySearch);
                preparedStatement.setInt(3, startPage);
                preparedStatement.setInt(4, 10);
                if (page <= 0) {
                    return questions;
                }
            } else if (type == QuestionQueryFacade.FIND_ALL_NO_ANSWER
                    || type == QuestionQueryFacade.FIND_ALL_NO_ANSWER_SORT_OLD
                    || type == QuestionQueryFacade.FIND_ALL_NO_ANSWER_SORT_VOTE) {
                int page = (int) key.get(0);
                int endPage = 10 * page;
                int startPage = endPage - 10;
                preparedStatement.setInt(1, startPage);
                preparedStatement.setInt(2, endPage);
                if (page <= 0) {
                    return questions;
                }
            } else if (type == QuestionQueryFacade.FIND_BY_ID_AND_RAW_TITLE) {
                int id = (int) key.get(0);
                String title_raw = (String) key.get(1);
                preparedStatement.setInt(1, id);
                preparedStatement.setString(2, title_raw);
            } else if (type == QuestionQueryFacade.FIND_BY_COMMENT_ID) {
                int id = (int) key.get(0);
                preparedStatement.setInt(1, id);
            } else if (type == QuestionQueryFacade.FIND_BY_USER_ID_SORT_NEW
                    || type == QuestionQueryFacade.FIND_BY_USER_ID_SORT_OLD) {
                preparedStatement.setInt(1, (int) key.get(0));
                int page = (int) key.get(1);
                int endPage = 10 * page;
                int startPage = endPage - 10;
                preparedStatement.setInt(2, startPage);
                preparedStatement.setInt(3, endPage);
                if (page <= 0) {
                    return questions;
                }
            } else if (type == QuestionQueryFacade.FIND_BY_TAG_NAME
                    || type == QuestionQueryFacade.FIND_BY_TAG_NAME_SORT_OLD
                    || type == QuestionQueryFacade.FIND_BY_TAG_NAME_SORT_VOTE) {
                preparedStatement.setString(1, (String) key.get(0));
                int page = (int) key.get(1);
                int endPage = 10 * page;
                int startPage = endPage - 10;
                preparedStatement.setInt(2, startPage);
                preparedStatement.setInt(3, endPage);
                if (page <= 0) {
                    return questions;
                }
            } else if (type == QuestionQueryFacade.FIND_RECOMMENDED) {
                int i = 1;
                for (Object dt : key) {
                    String tag = (String) dt;
                    preparedStatement.setString(i, tag);
                    i++;
                }
            }

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Question question = new Question();
                question.setId(resultSet.getInt(Question.COLUMN_ID));
                question.setTitle(resultSet.getString(Question.COLUMN_TITLE));
                question.setTitle_raw(resultSet.getString(Question.COLUMN_TITLE_RAW));
                if (type != QuestionQueryFacade.FIND_BY_ID_AND_RAW_TITLE && type != QuestionQueryFacade.FIND_ALL_TF_IDF && type != QuestionQueryFacade.FIND_BY_ID) {
                    question.setContent(StringUtils.abbreviate(resultSet.getString(Question.COLUMN_CONTENT), 300));
                } else {
                    question.setContent(resultSet.getString(Question.COLUMN_CONTENT));
                    question.setContent_raw(resultSet.getString(Question.COLUMN_CONTENT_RAW));
                }
                question.setCreateTime(resultSet.getTimestamp(Question.COLUMN_CREATE_TIME));
                question.setAuthor(UserConnection.getUserConnection().findById(resultSet.getInt(Question.COLUMN_USER_ID)));
                question.setTags(QuestionTagConnection.getQuestionTagConnection().findTagByQuestionId(resultSet.getInt(Question.COLUMN_ID)));
                question.setViews(resultSet.getInt(Question.COLUMN_VIEW));
                question.setPointPriority(resultSet.getInt(Question.COLUMN_POINT_PRIOITY));
                question.setVote(QuestionVoteConnection.getQuestionVoteConnection().getVoteByQuestionId(resultSet.getInt(Question.COLUMN_ID)));
                question.setCountAnswer(AnswerConnection.getAnswerConnection().getCountAnswerOfQuestionById(resultSet.getInt(Question.COLUMN_ID)));
                question.setVotedByUser(QuestionVoteConnection.getQuestionVoteConnection().checkVoted(resultSet.getInt(Question.COLUMN_ID), user_id));
//                if (type == QuestionQueryFacade.FIND_BY_ID_AND_RAW_TITLE) {
//                    question.setAnswers(
//                            AnswerConnection.getAnswerConnection().findByQuestionId(resultSet.getInt(Question.COLUMN_ID), user_id, 1)
//                    );
//                }
                //convert time
                ConvertTimestamp ct = new ConvertTimestamp();
                String timeConvert = ct.convertTimeNotify(resultSet.getTimestamp(Question.COLUMN_CREATE_TIME));
                question.setConvertTime(timeConvert);
                //
                int hasBestAnswer = this.checkHasBestAnswer(resultSet.getInt(Question.COLUMN_ID));
                question.setHasBestAnswer(hasBestAnswer);
                questions.add(question);
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuestionQueryFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return questions;
    }

    public int countQuestion(int type, ArrayList<Object> key) {
        int count = 0;
        String sql_query = "";
        if (type == QuestionQueryFacade.COUNT_ALL_QUESTION) {
            sql_query = "SELECT * FROM " + Question.TABLE_NAME;
        } else if (type == QuestionQueryFacade.COUNT_BY_USER_ID) {
            sql_query = "SELECT * FROM user a INNER JOIN question b ON b.user_id = a.id_user WHERE a.id_user = ?";
        } else if (type == QuestionQueryFacade.COUNT_SEARCH_QUESTION) {
            sql_query = "SELECT *, MATCH ("
                    + Question.COLUMN_TITLE
                    + ","
                    + Question.COLUMN_CONTENT
                    + ") AGAINST (?) as score FROM "
                    + Question.TABLE_NAME
                    + " WHERE MATCH ("
                    + Question.COLUMN_TITLE
                    + ","
                    + Question.COLUMN_CONTENT
                    + ") AGAINST (?) > 0 ORDER BY score DESC";
        } else if (type == QuestionQueryFacade.COUNT_SEARCH_QUESTION_BY_TAG) {
            sql_query = "SELECT a.id,a.title,a.content,a.create_time,a.user_id,a.point_priority,a.view,a.title_raw,a.content_raw "
                    + "FROM question a "
                    + "INNER JOIN question_tag b "
                    + "ON b.question_id=a.id "
                    + "INNER JOIN tag c "
                    + "ON c.id=b.tag_id "
                    + "WHERE c.name=?";
        }
        try {
            preparedStatement = DBConnection.getInstance().getConnection().prepareStatement(sql_query);
            if (type == QuestionQueryFacade.COUNT_BY_USER_ID) {
                preparedStatement.setInt(1, (int) key.get(0));
            } else if (type == QuestionQueryFacade.COUNT_SEARCH_QUESTION) {
                String keySearch = (String) key.get(0);
                preparedStatement.setString(1, keySearch);
                preparedStatement.setString(2, keySearch);
            } else if (type == QuestionQueryFacade.COUNT_SEARCH_QUESTION_BY_TAG) {
                preparedStatement.setString(1, (String) key.get(0));
            }
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.last()) {
                count = resultSet.getRow();
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuestionQueryFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }

    public int update(int type, Question question) {
        ConvertSEO convertSEO = new ConvertSEO();
        String title_raw = convertSEO.convertStringToSEO(question.getTitle());
        int question_id = 0;
        String sql_query = "";
        if (type == QuestionQueryFacade.ADD_NEW_QUESTION) {
            sql_query = "INSERT INTO "
                    + Question.TABLE_NAME
                    + "(" + Question.COLUMN_TITLE + ","
                    + Question.COLUMN_TITLE_RAW + ","
                    + Question.COLUMN_CONTENT + ","
                    + Question.COLUMN_CONTENT_RAW + ","
                    + Question.COLUMN_USER_ID + ","
                    + Question.COLUMN_POINT_PRIOITY + ","
                    + Question.COLUMN_VIEW + ") VALUES(?,?,?,?,?,?,?)";
        } else if (type == QuestionQueryFacade.EDIT_QUESTION) {
            sql_query = "UPDATE "
                    + Question.TABLE_NAME
                    + " SET "
                    + Question.COLUMN_TITLE + " = ?,"
                    + Question.COLUMN_TITLE_RAW + " = ?,"
                    + Question.COLUMN_CONTENT + " = ?,"
                    + Question.COLUMN_CONTENT_RAW + " = ?,"
                    + Question.COLUMN_POINT_PRIOITY + " = ?"
                    + " WHERE "
                    + Question.COLUMN_ID + " = ?";
        } else if (type == QuestionQueryFacade.UPDATE_VIEW) {
            sql_query = "UPDATE "
                    + Question.TABLE_NAME
                    + " SET "
                    + Question.COLUMN_VIEW + " = ?"
                    + " WHERE "
                    + Question.COLUMN_ID + " = ?";
        }
        try {
            preparedStatement = DBConnection.getInstance().getConnection().prepareStatement(sql_query, Statement.RETURN_GENERATED_KEYS);
            if (type == QuestionQueryFacade.ADD_NEW_QUESTION) {
                TFIDF tfidf = new TFIDF();
                tfidf.updateDictionary(question);
                preparedStatement.setString(1, question.getTitle());
                preparedStatement.setString(2, title_raw);
                preparedStatement.setString(3, question.getContent());
                preparedStatement.setString(4, question.getContent_raw());
                preparedStatement.setInt(5, question.getAuthor().getIdUser());
                preparedStatement.setInt(6, question.getPointPriority());
                preparedStatement.setInt(7, question.getViews());
                preparedStatement.executeUpdate();
                ResultSet rs = preparedStatement.getGeneratedKeys();
                if (rs.next()) {
                    question_id = rs.getInt(1);
                }
                //them follow
                QuestionFollowConnection.getQuestionFollowConnection().addNewFollow(question_id,
                        question.getAuthor().getIdUser(),
                        QuestionFollow.ROLE_AUTHOR
                );
            } else if (type == QuestionQueryFacade.EDIT_QUESTION) {
                preparedStatement.setString(1, question.getTitle());
                preparedStatement.setString(2, title_raw);
                preparedStatement.setString(3, question.getContent());
                preparedStatement.setString(4, question.getContent_raw());
                preparedStatement.setInt(5, question.getPointPriority());
                preparedStatement.setInt(6, question.getId());
                preparedStatement.executeUpdate();
            } else if (type == QuestionQueryFacade.UPDATE_VIEW) {
                preparedStatement.setInt(1, question.getViews());
                preparedStatement.setInt(2, question.getId());
                preparedStatement.executeUpdate();
            }

        } catch (SQLException ex) {
            Logger.getLogger(QuestionQueryFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return question_id;
    }

    public boolean delete(int type, ArrayList<Object> key) {
        String sql_query = "";
        if (type == QuestionQueryFacade.DELETE_BY_QUESTION_ID) {
            sql_query = "DELETE FROM "
                    + Question.TABLE_NAME
                    + " WHERE "
                    + Question.COLUMN_ID
                    + " = ?";
        }
        try {
            preparedStatement = DBConnection.getInstance().getConnection().prepareStatement(sql_query);
            if (type == QuestionQueryFacade.DELETE_BY_QUESTION_ID) {
                //xoa tag , vote, follow truoc
                int q_id = (int) key.get(0);
                QuestionTagConnection.getQuestionTagConnection().removeTag(q_id);
                QuestionVoteConnection.getQuestionVoteConnection().deleteByQuestionId(q_id);
                QuestionFollowConnection.getQuestionFollowConnection().deleteByQuestionId(q_id);
                AnswerConnection.getAnswerConnection().deleteByQuestionId(q_id);
                //
                preparedStatement.setInt(1, q_id);
            }
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(QuestionQueryFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    public int checkHasBestAnswer(int q_id) {
        String sql = "SELECT * FROM question a "
                + "INNER JOIN answer b "
                + "ON b.question_id=a.id "
                + "WHERE a.id=? && b.best_answer=1";

        try {
            preparedStatement = DBConnection.getInstance().getConnection().prepareStatement(sql);
            preparedStatement.setInt(1, q_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                return 1;
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuestionQueryFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
}
