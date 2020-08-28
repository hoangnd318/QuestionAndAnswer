/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.reply;

import dao.dbconnection.DBConnection;
import dao.notification.NotificationConnection;
import dao.thread_follow.ThreadFollowConnection;
import dao.user.UserConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Reply;
import model.ThreadFollow;
import service.ConvertTimestamp;

/**
 *
 * @author nguye
 */
public class ReplyQueryFacade {

    public static int GET_BY_THREAD_ID = 1;
    public static int GET_BY_ID = 2;

    public static int ADD_NEW = 1;
    public static int EDIT_REPLY = 2;

    public static int DELETE_BY_ID = 1;
    public static int DELETE_BY_THREAD_ID = 2;

    private PreparedStatement preparedStatement;

    public ReplyQueryFacade() {
        this.preparedStatement = null;
    }

    public ArrayList<Reply> find(int type, ArrayList<Object> key) {
        ArrayList<Reply> rs = new ArrayList<>();
        String sql_query = "";
        if (type == ReplyQueryFacade.GET_BY_THREAD_ID) {
            sql_query = "SELECT * FROM reply WHERE thread_id = ?";
        } else if (type == ReplyQueryFacade.GET_BY_ID) {
            sql_query = "SELECT * FROM reply WHERE id = ?";
        }
        try {
            preparedStatement = DBConnection.getInstance().getConnection().prepareStatement(sql_query);
            if (type == ReplyQueryFacade.GET_BY_THREAD_ID) {
                preparedStatement.setInt(1, (int) key.get(0));
            } else if (type == ReplyQueryFacade.GET_BY_ID) {
                preparedStatement.setInt(1, (int) key.get(0));
            }
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Reply r = new Reply();
                r.setId(resultSet.getInt(Reply.COLUMN_ID));
                r.setCreateTime(resultSet.getTimestamp(Reply.COLUMN_CREATE_TIME));
                r.setContent(resultSet.getString(Reply.COLUMN_CONTENT));
                r.setAuthor(UserConnection.getUserConnection().findById(resultSet.getInt(Reply.COLUMN_USER_ID)));
                ConvertTimestamp ct = new ConvertTimestamp();
                String timeConvert = ct.convertTimeNotify(resultSet.getTimestamp(Reply.COLUMN_CREATE_TIME));
                r.setConvertTime(timeConvert);

                rs.add(r);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReplyQueryFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    public int update(int type, Reply reply, int thread_id) {
        int reply_id = 0;
        String sql_query = "";
        if (type == ReplyQueryFacade.ADD_NEW) {
            sql_query = "INSERT INTO reply(content,thread_id,user_id) VALUES(?,?,?)";
        } else if (type == ReplyQueryFacade.EDIT_REPLY) {
            sql_query = "UPDATE reply SET content=? WHERE id=?";
        }
        try {
            preparedStatement = DBConnection.getInstance().getConnection().prepareStatement(sql_query, Statement.RETURN_GENERATED_KEYS);
            if (type == ReplyQueryFacade.ADD_NEW) {
                preparedStatement.setString(1, reply.getContent());
                preparedStatement.setInt(2, thread_id);
                preparedStatement.setInt(3, reply.getAuthor().getIdUser());
                preparedStatement.executeUpdate();
                ResultSet rs = preparedStatement.getGeneratedKeys();
                if (rs.next()) {
                    reply_id = rs.getInt(1);
                    reply.setId(reply_id);
                }

                //add follow
                boolean checkFollowed = ThreadFollowConnection.getAnswerFollowConnection().checkFollowed(thread_id, reply.getAuthor().getIdUser());
                if (!checkFollowed) {
                    ThreadFollowConnection.getAnswerFollowConnection().addFollow(thread_id, reply.getAuthor().getIdUser(), ThreadFollow.ROLE_GUEST);
                }
                //gui thong bao
                NotificationConnection.getNotificationConnection().sendNotificationWhenReply(reply, thread_id);
            } else if (type == ReplyQueryFacade.EDIT_REPLY) {
                preparedStatement.setString(1, reply.getContent());
                preparedStatement.setInt(2, reply.getId());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReplyQueryFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return reply_id;
    }

    public boolean delete(int type, ArrayList<Object> key) {
        String sql_query = "";

        if (type == ReplyQueryFacade.DELETE_BY_ID) {
            sql_query = "DELETE FROM reply WHERE id=?";
        } else if (type == ReplyQueryFacade.DELETE_BY_THREAD_ID) {
            sql_query = "DELETE FROM reply WHERE thread_id=?";
        }
        try {
            preparedStatement = DBConnection.getInstance().getConnection().prepareStatement(sql_query);
            if (type == ReplyQueryFacade.DELETE_BY_ID
                    || type == ReplyQueryFacade.DELETE_BY_THREAD_ID) {
                preparedStatement.setInt(1, (int) key.get(0));
            }
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ReplyQueryFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
}
