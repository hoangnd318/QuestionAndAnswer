/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.thread_follow;

import dao.dbconnection.DBConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nguye
 */
public class ThreadFollowFacade {

    public static int ADD_NEW_FOLLOW = 1;

    private PreparedStatement preparedStatement;

    public ThreadFollowFacade() {
        this.preparedStatement = null;
    }

    public String get(int thread_id) {
        String follow = "";
        String sql_query = "SELECT a.user_id FROM thread_follow a WHERE a.thread_id=?";
        try {
            preparedStatement = DBConnection.getInstance().getConnection().prepareStatement(sql_query);
            preparedStatement.setInt(1, thread_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                follow += resultSet.getInt("user_id") + ",";
            }
        } catch (SQLException ex) {
            Logger.getLogger(ThreadFollowFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return follow;
    }
    
    public boolean update(int type, int thread_id, int user_id, String role) {
        String sql_query = null;
        if (type == ThreadFollowFacade.ADD_NEW_FOLLOW) {
            sql_query = "INSERT INTO thread_follow(role,thread_id,user_id) VALUES(?,?,?)";
        }
        try {
            preparedStatement = DBConnection.getInstance().getConnection().prepareStatement(sql_query);
            if (type == ThreadFollowFacade.ADD_NEW_FOLLOW) {
                preparedStatement.setString(1, role);
                preparedStatement.setInt(2, thread_id);
                preparedStatement.setInt(3, user_id);
            }
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ThreadFollowFacade.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    public boolean checkFollowed(int thread_id, int user_id) {
        String sql_query = "SELECT * FROM thread_follow WHERE thread_id = ? && user_id = ?";
        try {
            preparedStatement = DBConnection.getInstance().getConnection().prepareStatement(sql_query);
            preparedStatement.setInt(1, thread_id);
            preparedStatement.setInt(2, user_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ThreadFollowFacade.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    public boolean delete(int thread_id) {
        String sql_query = "DELETE FROM thread_follow WHERE thread_id = ?";
        try {
            preparedStatement = DBConnection.getInstance().getConnection().prepareStatement(sql_query);
            preparedStatement.setInt(1, thread_id);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ThreadFollowFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
}
