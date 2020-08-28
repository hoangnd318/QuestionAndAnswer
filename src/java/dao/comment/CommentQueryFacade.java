/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.comment;

import dao.answer.AnswerConnection;
import dao.answer_follow.AnswerFollowConnection;
import dao.dbconnection.DBConnection;
import dao.notification.NotificationConnection;
import dao.user.UserConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Answer;
import model.Comment;
import model.AnswerFollow;
import service.ConvertTimestamp;

/**
 *
 * @author nguye
 */
public class CommentQueryFacade {

    public static int ADD_NEW_COMMENT = 1;
    public static int EDIT_COMMENT = 2;
    //
    public static int GET_COMMENT_BY_ANSWER_ID = 1;
    public static int CHECK_AUTHOR_QUESTION_ID = 1;
    public static int CHECK_AUTHOR_ANSWER_ID = 2;

    public static int DELETE_BY_ANSWER_ID = 1;
    public static int DELETE_BY_ID = 2;

    private PreparedStatement preparedStatement;

    public CommentQueryFacade() {
        this.preparedStatement = null;
    }

    public ArrayList<Comment> find(int type, ArrayList<Object> key) {
        ArrayList<Comment> comments = new ArrayList<>();
        String sql_query = "";
        if (type == CommentQueryFacade.GET_COMMENT_BY_ANSWER_ID) {
            sql_query = "SELECT * FROM "
                    + Comment.TABLE_NAME
                    + " WHERE "
                    + Comment.COLUMN_ANSWER_ID
                    + " = ?";
        }

        try {
            preparedStatement = DBConnection.getInstance().getConnection().prepareStatement(sql_query);
            if (type == CommentQueryFacade.GET_COMMENT_BY_ANSWER_ID) {
                int answer_id = (int) key.get(0);
                preparedStatement.setInt(1, answer_id);
            }
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Comment comment = new Comment();
                comment.setId(resultSet.getInt(Comment.COLUMN_ID));
                comment.setContent(resultSet.getString(Comment.COLUMN_CONTENT));
                comment.setAuthor(UserConnection.getUserConnection().findById(resultSet.getInt(Comment.COLUMN_USER_ID)));
                comment.setCreateTime(resultSet.getTimestamp(Comment.COLUMN_CREATE_TIME));
                //convert time
                ConvertTimestamp ct = new ConvertTimestamp();
                String timeConvert = ct.convertTimeNotify(resultSet.getTimestamp(Comment.COLUMN_CREATE_TIME));
                comment.setConvertTime(timeConvert);
                comments.add(comment);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CommentQueryFacade.class.getName()).log(Level.SEVERE, null, ex);
        }

        return comments;
    }

    public int update(int type, Comment comment, int answer_id) {
        int comment_id = 0;
        String sql_query = "";
        if (type == CommentQueryFacade.ADD_NEW_COMMENT) {
            sql_query = "INSERT INTO "
                    + Comment.TABLE_NAME
                    + "(" + Comment.COLUMN_CONTENT + ","
                    + Comment.COLUMN_USER_ID + ","
                    + Comment.COLUMN_ANSWER_ID + ") VALUES(?,?,?)";
        } else if (type == CommentQueryFacade.EDIT_COMMENT) {
            sql_query = "UPDATE "
                    + Comment.TABLE_NAME
                    + " SET "
                    + Comment.COLUMN_CONTENT + " = ?"
                    + " WHERE "
                    + Comment.COLUMN_ID + " = ?";
        }
        try {
            preparedStatement = DBConnection.getInstance().getConnection().prepareStatement(sql_query, Statement.RETURN_GENERATED_KEYS);
            if (type == CommentQueryFacade.ADD_NEW_COMMENT) {
                preparedStatement.setString(1, comment.getContent());
                preparedStatement.setInt(2, comment.getAuthor().getIdUser());
                preparedStatement.setInt(3, answer_id);
                preparedStatement.executeUpdate();
                ResultSet rs = preparedStatement.getGeneratedKeys();
                if (rs.next()) {
                    comment_id = rs.getInt(1);
                    comment.setId(comment_id);
                }
                //kiem tra xem co phai chu answer comment hay k
                boolean check1 = CommentConnection.getCommentConnection().checkAnswerId(comment_id);
                //kiem tra xem co phai chu question comment hay k
                boolean check2 = CommentConnection.getCommentConnection().checkQuestionId(comment_id);
                //neu k phai ca 2 thi them theo doi
                if (!check1 && !check2) {
                    //them follow
                    if (!AnswerFollowConnection.getAnswerFollowConnection().checkFollowed(answer_id, comment.getAuthor().getIdUser())) {
                        AnswerFollowConnection.getAnswerFollowConnection().addNewFollow(answer_id,
                                comment.getAuthor().getIdUser(),
                                AnswerFollow.ROLE_GUEST
                        );
                    }
                }
                //gui thong bao
                NotificationConnection.getNotificationConnection().sendNotificationWhenComment(comment, answer_id);
            } else if (type == CommentQueryFacade.EDIT_COMMENT) {
                preparedStatement.setString(1, comment.getContent());
                preparedStatement.setInt(2, comment.getId());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException ex) {
            Logger.getLogger(CommentQueryFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return comment_id;
    }

    public boolean checkIdUser(int type, int comment_id) {
        String sql_query = "";
        if (type == CommentQueryFacade.CHECK_AUTHOR_QUESTION_ID) {
            sql_query = "SELECT * FROM question a "
                    + "INNER JOIN answer b ON b.question_id = a.id "
                    + "INNER JOIN comment c ON c.answer_id = b.id WHERE "
                    + "c.id = ? && "
                    + "c.user_id = a.user_id";
        } else if (type == CommentQueryFacade.CHECK_AUTHOR_ANSWER_ID) {
            sql_query = "SELECT * FROM answer a "
                    + "INNER JOIN comment b ON b.answer_id = a.id WHERE "
                    + "b.id = ? && "
                    + "b.user_id = a.user_id";
        }
        try {
            preparedStatement = DBConnection.getInstance().getConnection().prepareStatement(sql_query);
            preparedStatement.setInt(1, comment_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CommentQueryFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean delete(int type, ArrayList<Object> key) {
        String sql_query = "";
        if (type == CommentQueryFacade.DELETE_BY_ANSWER_ID) {
            sql_query = "DELETE FROM "
                    + Comment.TABLE_NAME
                    + " WHERE "
                    + Comment.COLUMN_ANSWER_ID
                    + " = ?";
        } else if (type == CommentQueryFacade.DELETE_BY_ID) {
            sql_query = "DELETE FROM "
                    + Comment.TABLE_NAME
                    + " WHERE "
                    + Comment.COLUMN_ID
                    + " = ?";
        }
        try {
            preparedStatement = DBConnection.getInstance().getConnection().prepareStatement(sql_query);
            preparedStatement.setInt(1, (int) key.get(0));
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CommentQueryFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
}
