/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.answer;

import dao.answer_follow.AnswerFollowConnection;
import dao.answer_vote.AnswerVoteConnection;
import dao.comment.CommentConnection;
import dao.dbconnection.DBConnection;
import dao.notification.NotificationConnection;
import dao.question.QuestionConnection;
import dao.question_follow.QuestionFollowConnection;
import dao.user.UserConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Answer;
import model.Notification;
import model.Question;
import model.AnswerFollow;
import model.QuestionFollow;
import model.User;
import service.ConvertTimestamp;
import service.CreateUrlNotification;

/**
 *
 * @author nguye
 */
public class AnswerQueryFacade {

    public static int ADD_NEW_ANSWER = 1;
    public static int EDIT_ANSWER = 2;
    public static int VOTE_BEST_ANSWER = 3;

    public static int GET_ANSWER_BY_QUESTION_ID = 1;
    public static int GET_ANSWER_BY_USER_ID_SORT_NEW = 2;
    public static int GET_ANSWER_BY_USER_ID_SORT_OLD = 4;
    public static int GET_BY_ANSWER_ID = 3;
    public static int GET_ANSWER_BY_QUESTION_ID_SORT_OLD = 5;
    public static int GET_ANSWER_BY_QUESTION_ID_SORT_VOTE = 6;

    //
    public static int GET_COUNT_ANSWER_OF_QUESTION = 1;
    public static int COUNT_BY_USER_ID = 2;
    public static int COUNT_BEST_ANSWER_BY_USER_ID = 3;
    //
    public static int DELETE_BY_QUESTION_ID = 1;
    public static int DELETE_BY_ANSWER_ID = 2;

    private PreparedStatement preparedStatement;

    public AnswerQueryFacade() {
        this.preparedStatement = null;
    }

    public ArrayList<Answer> find(int type, ArrayList<Object> key, int user_id) {
        ArrayList<Answer> answers = new ArrayList<>();
        String sql_query = "";
        if (type == AnswerQueryFacade.GET_ANSWER_BY_QUESTION_ID) {
            sql_query = "SELECT * FROM answer a "
                    + "WHERE a.question_id = ? "
                    + "ORDER BY a.create_time DESC "
                    + "LIMIT ?,?";
        } else if (type == AnswerQueryFacade.GET_ANSWER_BY_QUESTION_ID_SORT_OLD) {
            sql_query = "SELECT * FROM answer a "
                    + "WHERE a.question_id = ? "
                    + "ORDER BY a.create_time ASC "
                    + "LIMIT ?,?";
        } else if (type == AnswerQueryFacade.GET_ANSWER_BY_QUESTION_ID_SORT_VOTE) {
            sql_query = "SELECT *,"
                    + "((SELECT COUNT(*) total FROM answer_vote b WHERE b.answer_id = a.id&&b.type='vote_up')-(SELECT COUNT(*) total FROM answer_vote b WHERE b.answer_id = a.id&&b.type='vote_down')) AS vote "
                    + "FROM answer a "
                    + "WHERE a.question_id=? "
                    + "ORDER BY vote DESC "
                    + "LIMIT ?,?";
        } else if (type == AnswerQueryFacade.GET_ANSWER_BY_USER_ID_SORT_NEW) {
            sql_query = "SELECT "
                    + "b.id,"
                    + "b.content,"
                    + "b.create_time,"
                    + "b.best_answer,"
                    + "b.question_id,"
                    + "b.user_id,"
                    + "b.content_raw "
                    + "FROM user a "
                    + "INNER JOIN answer b "
                    + "ON b.user_id=a.id_user "
                    + "WHERE a.id_user=?"
                    + " ORDER BY b.create_time DESC "
                    + " LIMIT ?,?";
        } else if (type == AnswerQueryFacade.GET_ANSWER_BY_USER_ID_SORT_OLD) {
            sql_query = "SELECT "
                    + "b.id,"
                    + "b.content,"
                    + "b.create_time,"
                    + "b.best_answer,"
                    + "b.question_id,"
                    + "b.user_id,"
                    + "b.content_raw "
                    + "FROM user a "
                    + "INNER JOIN answer b "
                    + "ON b.user_id=a.id_user "
                    + "WHERE a.id_user=?"
                    + " ORDER BY b.create_time ASC "
                    + " LIMIT ?,?";
        } else if (type == AnswerQueryFacade.GET_BY_ANSWER_ID) {
            sql_query = "SELECT * FROM "
                    + Answer.TABLE_NAME
                    + " WHERE "
                    + Answer.COLUMN_ID
                    + " = ?";
        }

        try {
            preparedStatement = DBConnection.getInstance().getConnection().prepareStatement(sql_query);
            if (type == AnswerQueryFacade.GET_ANSWER_BY_QUESTION_ID
                    || type == AnswerQueryFacade.GET_ANSWER_BY_QUESTION_ID_SORT_OLD
                    || type == AnswerQueryFacade.GET_ANSWER_BY_QUESTION_ID_SORT_VOTE) {
                int question_id = (int) key.get(0);
                preparedStatement.setInt(1, question_id);
                int page = (int) key.get(1);
                int endPage = 10 * page;
                int startPage = endPage - 10;
                preparedStatement.setInt(2, startPage);
                preparedStatement.setInt(3, endPage);
                if (page <= 0) {
                    return answers;
                }
            } else if (type == AnswerQueryFacade.GET_ANSWER_BY_USER_ID_SORT_NEW
                    || type == AnswerQueryFacade.GET_ANSWER_BY_USER_ID_SORT_OLD) {
                preparedStatement.setInt(1, (int) key.get(0));
                int page = (int) key.get(1);
                int endPage = 10 * page;
                int startPage = endPage - 10;
                preparedStatement.setInt(2, startPage);
                preparedStatement.setInt(3, endPage);
                if (page <= 0) {
                    return answers;
                }
            } else if (type == AnswerQueryFacade.GET_BY_ANSWER_ID) {
                preparedStatement.setInt(1, (int) key.get(0));
            }
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Answer answer = new Answer();
                answer.setId(resultSet.getInt(Answer.COLUMN_ID));
                answer.setContent(resultSet.getString(Answer.COLUMN_CONTENT));
                answer.setContent_raw(resultSet.getString(Answer.COLUMN_CONTENT_RAW));
                answer.setAuthor(UserConnection.getUserConnection().findById(resultSet.getInt(Answer.COLUMN_USER_ID)));
                answer.setCreateTime(resultSet.getTimestamp(Answer.COLUMN_CREATE_TIME));
                answer.setBestAnswer(resultSet.getInt(Answer.COLUMN_BEST_ANSWER));
                answer.setVote(AnswerVoteConnection.getAnswerVoteConnection().getVoteByAnswerId(resultSet.getInt(Answer.COLUMN_ID)));
                answer.setVotedByUser(AnswerVoteConnection.getAnswerVoteConnection().checkVoted(resultSet.getInt(Answer.COLUMN_ID), user_id));
                answer.setComments(CommentConnection.getCommentConnection().getByAnswerId(resultSet.getInt(Answer.COLUMN_ID)));
                //convert time
                ConvertTimestamp ct = new ConvertTimestamp();
                String timeConvert = ct.convertTimeNotify(resultSet.getTimestamp(Answer.COLUMN_CREATE_TIME));
                answer.setConvertTime(timeConvert);
                answer.setQuestion(QuestionConnection.getQuestionConnection().findById(resultSet.getInt(Answer.COLUMN_QUESTION_ID)).get(0));
                answers.add(answer);
            }

        } catch (SQLException ex) {
            Logger.getLogger(AnswerQueryFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return answers;
    }

    public int getCountAnswer(int type, ArrayList<Object> key) {
        int count = 0;
        String sql_query = "";
        if (type == AnswerQueryFacade.GET_COUNT_ANSWER_OF_QUESTION) {
            sql_query = "SELECT * FROM " + Answer.TABLE_NAME + " WHERE " + Answer.COLUMN_QUESTION_ID + " = ?";
        } else if (type == AnswerQueryFacade.COUNT_BY_USER_ID) {
            sql_query = "SELECT * FROM user a INNER JOIN answer b ON b.user_id = a.id_user WHERE a.id_user = ?";
        } else if (type == AnswerQueryFacade.COUNT_BEST_ANSWER_BY_USER_ID) {
            sql_query = "SELECT * FROM user a INNER JOIN answer b ON b.user_id = a.id_user WHERE a.id_user = ? AND b.best_answer = ?";
        }
        try {
            preparedStatement = DBConnection.getInstance().getConnection().prepareStatement(sql_query);
            if (type == AnswerQueryFacade.GET_COUNT_ANSWER_OF_QUESTION) {
                preparedStatement.setInt(1, (int) key.get(0));
            } else if (type == AnswerQueryFacade.COUNT_BY_USER_ID) {
                preparedStatement.setInt(1, (int) key.get(0));
            } else if (type == AnswerQueryFacade.COUNT_BEST_ANSWER_BY_USER_ID) {
                preparedStatement.setInt(1, (int) key.get(0));
                preparedStatement.setInt(2, 1);
            }
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.last()) {
                count = resultSet.getRow();
            }
        } catch (SQLException ex) {
            Logger.getLogger(AnswerQueryFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }

    public int update(int type, Answer answer, int question_id) {
        int answer_id = 0;
        String sql_query = "";
        if (type == AnswerQueryFacade.ADD_NEW_ANSWER) {
            sql_query = "INSERT INTO "
                    + Answer.TABLE_NAME
                    + "(" + Answer.COLUMN_CONTENT + ","
                    + Answer.COLUMN_CONTENT_RAW + ","
                    + Answer.COLUMN_BEST_ANSWER + ","
                    + Answer.COLUMN_QUESTION_ID + ","
                    + Answer.COLUMN_USER_ID + ") VALUES(?,?,?,?,?)";
        } else if (type == AnswerQueryFacade.EDIT_ANSWER) {
            sql_query = "UPDATE "
                    + Answer.TABLE_NAME
                    + " SET "
                    + Answer.COLUMN_CONTENT + " = ?,"
                    + Answer.COLUMN_CONTENT_RAW + " = ?"
                    + " WHERE "
                    + Answer.COLUMN_ID + " = ?";
        } else if (type == AnswerQueryFacade.VOTE_BEST_ANSWER) {
            sql_query = "UPDATE "
                    + Answer.TABLE_NAME
                    + " SET "
                    + Answer.COLUMN_BEST_ANSWER + " = ?"
                    + " WHERE "
                    + Answer.COLUMN_ID + " = ?";
        }
        try {
            preparedStatement = DBConnection.getInstance().getConnection().prepareStatement(sql_query, Statement.RETURN_GENERATED_KEYS);
            if (type == AnswerQueryFacade.ADD_NEW_ANSWER) {
                preparedStatement.setString(1, answer.getContent());
                preparedStatement.setString(2, answer.getContent_raw());
                preparedStatement.setInt(3, answer.getBestAnswer());
                preparedStatement.setInt(4, question_id);
                preparedStatement.setInt(5, answer.getAuthor().getIdUser());
                preparedStatement.executeUpdate();
                ResultSet rs = preparedStatement.getGeneratedKeys();
                if (rs.next()) {
                    answer_id = rs.getInt(1);
                    answer.setId(answer_id);
                }
                //them follow
                int author_question_id = QuestionConnection
                        .getQuestionConnection()
                        .findById(question_id)
                        .get(0)
                        .getAuthor()
                        .getIdUser();
                //check neu chinh n binh luan
                if (author_question_id != answer.getAuthor().getIdUser()) {
                    if (!QuestionFollowConnection.getQuestionFollowConnection().checkFollowed(question_id, answer.getAuthor().getIdUser())) {
                        QuestionFollowConnection.getQuestionFollowConnection().addNewFollow(question_id,
                                answer.getAuthor().getIdUser(),
                                QuestionFollow.ROLE_GUEST
                        );
                    }
                    if (!AnswerFollowConnection.getAnswerFollowConnection().checkFollowed(answer_id, answer.getAuthor().getIdUser())) {
                        AnswerFollowConnection.getAnswerFollowConnection().addNewFollow(answer_id,
                                answer.getAuthor().getIdUser(),
                                AnswerFollow.ROLE_AUTHOR
                        );
                    }
                    if (!AnswerFollowConnection.getAnswerFollowConnection().checkFollowed(answer_id, author_question_id)) {
                        AnswerFollowConnection.getAnswerFollowConnection().addNewFollow(answer_id,
                                author_question_id,
                                AnswerFollow.ROLE_AUTHOR_QUESTION
                        );
                    }
                }
                //gui thong bao
                NotificationConnection.getNotificationConnection().sendNotificationNewAnswer(answer, question_id);
                //cong diem
                addPoint(answer.getAuthor());
            } else if (type == AnswerQueryFacade.EDIT_ANSWER) {
                preparedStatement.setString(1, answer.getContent());
                preparedStatement.setString(2, answer.getContent_raw());
                preparedStatement.setInt(3, answer.getId());
                preparedStatement.executeUpdate();
            } else if (type == AnswerQueryFacade.VOTE_BEST_ANSWER) {
                preparedStatement.setInt(1, 1);
                preparedStatement.setInt(2, answer.getId());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException ex) {
            Logger.getLogger(AnswerQueryFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return answer_id;
    }

    //luu thong bao
    public void sendNotification(int answer_id, String sender_name, int question_id) {
        //lay thong tin cau hoi
        Question question = QuestionConnection.getQuestionConnection().findById(question_id).get(0);
        User author = QuestionConnection.getQuestionConnection().findById(question_id).get(0).getAuthor();

        //tao thong bao
        Notification n = new Notification();
        n.setRecipient(question.getAuthor()); // nguoi nhan la tac gia cua cau hoi
        n.setUrl(
                CreateUrlNotification.getCreateUrlService().createNotifyAddAnswerUrl(
                        question_id,
                        question.getTitle_raw(),
                        answer_id
                )
        );
        String content = "<span>" + sender_name + "</span>" + " đã trả lời câu hỏi của bạn.";
        n.setContent(content);
        //luu thong bao vao db
        NotificationConnection.getNotificationConnection().addNewNotification(n);
    }

    public boolean delete(int type, ArrayList<Object> key) {

        String sql_query = "";
        String sql_tmp = "";
        if (type == AnswerQueryFacade.DELETE_BY_QUESTION_ID) {
            sql_query = "DELETE FROM "
                    + Answer.TABLE_NAME
                    + " WHERE "
                    + Answer.COLUMN_QUESTION_ID
                    + " = ?";

            sql_tmp = "SELECT * FROM "
                    + Answer.TABLE_NAME
                    + " WHERE "
                    + Answer.COLUMN_QUESTION_ID
                    + " = ?";
        } else if (type == AnswerQueryFacade.DELETE_BY_ANSWER_ID) {
            sql_query = "DELETE FROM "
                    + Answer.TABLE_NAME
                    + " WHERE "
                    + Answer.COLUMN_ID
                    + " = ?";
        }
        try {
            preparedStatement = DBConnection.getInstance().getConnection().prepareStatement(sql_query);
            if (type == AnswerQueryFacade.DELETE_BY_QUESTION_ID) {
                //xoa follow, comment, vote trc
                PreparedStatement ps_tmp = DBConnection.getInstance().getConnection().prepareStatement(sql_tmp);
                ps_tmp.setInt(1, (int) key.get(0));
                ResultSet rs = ps_tmp.executeQuery();
                while (rs.next()) {
                    int a_id = rs.getInt(Answer.COLUMN_ID);
                    AnswerFollowConnection.getAnswerFollowConnection().deleteByAnswerId(a_id);
                    CommentConnection.getCommentConnection().deleteByAnswerId(a_id);
                    AnswerVoteConnection.getAnswerVoteConnection().deleteByAnswerId(a_id);
                }
                //
                preparedStatement.setInt(1, (int) key.get(0));
            } else if (type == AnswerQueryFacade.DELETE_BY_ANSWER_ID) {
                int a_id = (int) key.get(0);
                AnswerFollowConnection.getAnswerFollowConnection().deleteByAnswerId(a_id);
                CommentConnection.getCommentConnection().deleteByAnswerId(a_id);
                AnswerVoteConnection.getAnswerVoteConnection().deleteByAnswerId(a_id);
                preparedStatement.setInt(1, (int) key.get(0));
            }

            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AnswerQueryFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    //cong diem ng dung
    public void addPoint(User user) {
        user.setPointQA(user.getPointQA() + 5);
        UserConnection.getUserConnection().updatePoint(user);
    }
}
