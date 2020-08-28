/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.group_follow;

import dao.dbconnection.DBConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.GroupFollow;

/**
 *
 * @author nguye
 */
public class GroupFollowFacade {

    public static int CHECK_FOLLOW = 1;
    public static int CHECK_ADMIN = 2;

    private PreparedStatement preparedStatement;

    public GroupFollowFacade() {
        this.preparedStatement = null;
    }

    public String get(int group_id) {
        String follow = "";
        String sql_query = "SELECT a.user_id FROM group_follow a WHERE a.group_id=?";
        try {
            preparedStatement = DBConnection.getInstance().getConnection().prepareStatement(sql_query);
            preparedStatement.setInt(1, group_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                follow += resultSet.getInt("user_id") + ",";
            }
        } catch (SQLException ex) {
            Logger.getLogger(GroupFollowFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return follow;
    }
    
    public boolean addFollow(int group_id, int user_id, String role) {
        String sql_query = "INSERT INTO group_follow(role,group_id,user_id) VALUES(?,?,?)";
        try {
            preparedStatement = DBConnection.getInstance().getConnection().prepareStatement(sql_query);
            preparedStatement.setString(1, role);
            preparedStatement.setInt(2, group_id);
            preparedStatement.setInt(3, user_id);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(GroupFollowFacade.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    public boolean unFollow(int group_id, int user_id) {
        String sql_query = "DELETE FROM group_follow WHERE group_id = ? && user_id = ?";
        try {
            preparedStatement = DBConnection.getInstance().getConnection().prepareStatement(sql_query);
            preparedStatement.setInt(1, group_id);
            preparedStatement.setInt(2, user_id);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(GroupFollowFacade.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    public boolean checkFollow(int type, int group_id, int user_id) {
        String sql_query = "";
        if (type == GroupFollowFacade.CHECK_FOLLOW) {
            sql_query = "SELECT * FROM group_follow WHERE group_id = ? && user_id = ?";
        } else if (type == GroupFollowFacade.CHECK_ADMIN) {
            sql_query = "SELECT * FROM thread a INNER JOIN groups b ON b.id = a.group_id INNER JOIN group_follow c ON c.group_id = b.id WHERE a.id = ? && c.user_id = ? && c.role = ?";
        }
        try {
            preparedStatement = DBConnection.getInstance().getConnection().prepareStatement(sql_query);
            preparedStatement.setInt(1, group_id);
            preparedStatement.setInt(2, user_id);
            if(type == GroupFollowFacade.CHECK_ADMIN){
                preparedStatement.setString(3, "admin");
            }
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(GroupFollowFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
