/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.threads;

import dao.dbconnection.DBConnection;
import dao.group_follow.GroupFollowConnection;
import dao.notification.NotificationConnection;
import dao.reply.ReplyConnection;
import dao.thread_follow.ThreadFollowConnection;
import dao.user.UserConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.ThreadFollow;
import model.Threads;
import service.ConvertSEO;
import service.ConvertTimestamp;

/**
 *
 * @author nguye
 */
public class ThreadQueryFacade {

    public static int GET_BY_GROUP_ID = 1;
    public static int GET_BY_THREAD_ID = 2;
    public static int GET_BY_ONLY_ID = 3;

    public static int ADD_NEW = 1;
    public static int EDIT_THREAD = 2;

    private PreparedStatement preparedStatement;

    public ThreadQueryFacade() {
        this.preparedStatement = null;
    }

    public ArrayList<Threads> find(int type, ArrayList<Object> key) {
        ArrayList<Threads> threads = new ArrayList<>();
        String sql_query = "";
        if (type == ThreadQueryFacade.GET_BY_GROUP_ID) {
            sql_query = "SELECT * FROM thread WHERE group_id = ?";
        } else if (type == ThreadQueryFacade.GET_BY_THREAD_ID) {
            sql_query = "SELECT * FROM thread WHERE id = ? && title_raw = ?";
        } else if (type == ThreadQueryFacade.GET_BY_ONLY_ID) {
            sql_query = "SELECT * FROM thread WHERE id = ?";
        }
        try {
            preparedStatement = DBConnection.getInstance().getConnection().prepareStatement(sql_query);
            if (type == ThreadQueryFacade.GET_BY_GROUP_ID) {
                preparedStatement.setInt(1, (int) key.get(0));
            } else if (type == ThreadQueryFacade.GET_BY_THREAD_ID) {
                preparedStatement.setInt(1, (int) key.get(0));
                preparedStatement.setString(2, (String) key.get(1));
            } else if (type == ThreadQueryFacade.GET_BY_ONLY_ID) {
                preparedStatement.setInt(1, (int) key.get(0));
            }
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Threads t = new Threads();
                t.setId(resultSet.getInt(Threads.COLUMN_ID));
                t.setCreateTime(resultSet.getTimestamp(Threads.COLUMN_CREATE_TIME));
                t.setTitle(resultSet.getString(Threads.COLUMN_TITLE));
                t.setTitleRaw(resultSet.getString(Threads.COLUMN_TITLE_RAW));
                t.setAuthor(
                        UserConnection.getUserConnection().findById(resultSet.getInt(Threads.COLUMN_USER_ID))
                );
                ConvertTimestamp ct = new ConvertTimestamp();
                String timeConvert = ct.convertTimeNotify(resultSet.getTimestamp(Threads.COLUMN_CREATE_TIME));
                t.setConvertTime(timeConvert);
                if (type != ThreadQueryFacade.GET_BY_GROUP_ID) {
                    t.setContent(resultSet.getString(Threads.COLUMN_CONTENT));
                    t.setListReply(ReplyConnection.getReplyConnection().getByThreadId(resultSet.getInt(Threads.COLUMN_ID)));
                }

                threads.add(t);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ThreadQueryFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return threads;
    }

    public int update(int type, Threads thread, int group_id) {
        int thread_id = 0;
        ConvertSEO convertSEO = new ConvertSEO();
        String title_raw = convertSEO.convertStringToSEO(thread.getTitle());
        String sql_query = "";
        if (type == ThreadQueryFacade.ADD_NEW) {
            sql_query = "INSERT INTO thread(content,title,title_raw,group_id,user_id) VALUES(?,?,?,?,?)";
        } else if (type == ThreadQueryFacade.EDIT_THREAD) {
            sql_query = "UPDATE thread SET content=?,title=?,title_raw=? WHERE id=?";
        }
        try {
            preparedStatement = DBConnection.getInstance().getConnection().prepareStatement(sql_query, Statement.RETURN_GENERATED_KEYS);
            if (type == ThreadQueryFacade.ADD_NEW) {
                preparedStatement.setString(1, thread.getContent());
                preparedStatement.setString(2, thread.getTitle());
                preparedStatement.setString(3, title_raw);
                preparedStatement.setInt(4, group_id);
                preparedStatement.setInt(5, thread.getAuthor().getIdUser());
                preparedStatement.executeUpdate();
                ResultSet rs = preparedStatement.getGeneratedKeys();
                if (rs.next()) {
                    thread_id = rs.getInt(1);
                    thread.setId(thread_id);
                }
                //add theo doi
                ThreadFollowConnection.getAnswerFollowConnection().addFollow(thread_id, thread.getAuthor().getIdUser(), ThreadFollow.ROLE_GUEST);
                //gui thong bao
                NotificationConnection.getNotificationConnection().sendNotificationWhenThread(thread, group_id);
            } else if (type == ThreadQueryFacade.EDIT_THREAD) {
                preparedStatement.setString(1, thread.getContent());
                preparedStatement.setString(2, thread.getTitle());
                preparedStatement.setString(3, title_raw);
                preparedStatement.setInt(4, thread.getId());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ThreadQueryFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return thread_id;
    }

    public boolean delete(int thread_id) {
        String sql_query = "DELETE FROM thread WHERE id = ?";
        try {
            preparedStatement = DBConnection.getInstance().getConnection().prepareStatement(sql_query);
            preparedStatement.setInt(1, thread_id);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ThreadQueryFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
}
