/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.question_follow;

import dao.dbconnection.DBConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.QuestionFollow;

/**
 *
 * @author nguye
 */
public class QuestionFollowFacade {

    public static int ADD_NEW_FOLLOW = 1;
    public static int UPDATE_FOLLOW = 2;

    public static int DELETE_BY_QUESTION_ID = 1;

    private PreparedStatement preparedStatement;

    public QuestionFollowFacade() {
        this.preparedStatement = null;
    }

    public String get(int question_id) {
        String follow = "";
        String sql_query = "SELECT a.user_id "
                + "FROM question_follow a "
                + "WHERE a.question_id=?";
        try {
            preparedStatement = DBConnection.getInstance().getConnection().prepareStatement(sql_query);
            preparedStatement.setInt(1, question_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                follow += resultSet.getInt(QuestionFollow.COLUMN_USER_ID) + ",";
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuestionFollowFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return follow;
    }

    public boolean update(int type, int question_id, int user_id, String role) {
        String sql_query = null;
        if (type == QuestionFollowFacade.ADD_NEW_FOLLOW) {
            sql_query = "INSERT INTO "
                    + QuestionFollow.TABLE_NAME
                    + "(" + QuestionFollow.COLUMN_ROLE + ","
                    + QuestionFollow.COLUMN_QUESTION_ID + ","
                    + QuestionFollow.COLUMN_USER_ID + ") VALUES(?,?,?)";
        }
        try {
            preparedStatement = DBConnection.getInstance().getConnection().prepareStatement(sql_query);
            if (type == QuestionFollowFacade.ADD_NEW_FOLLOW) {
                preparedStatement.setString(1, role);
                preparedStatement.setInt(2, question_id);
                preparedStatement.setInt(3, user_id);
            }
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(QuestionFollowFacade.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    public boolean checkFollowed(int question_id, int user_id) {
        String sql_query = "SELECT * FROM "
                + QuestionFollow.TABLE_NAME
                + " WHERE "
                + QuestionFollow.COLUMN_USER_ID
                + " = ? &&"
                + QuestionFollow.COLUMN_QUESTION_ID
                + " = ?";
        try {
            preparedStatement = DBConnection.getInstance().getConnection().prepareStatement(sql_query);
            preparedStatement.setInt(1, user_id);
            preparedStatement.setInt(2, question_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuestionFollowFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean delete(int type, ArrayList<Object> key) {
        String sql_query = "";
        if (type == QuestionFollowFacade.DELETE_BY_QUESTION_ID) {
            sql_query = "DELETE FROM "
                    + QuestionFollow.TABLE_NAME
                    + " WHERE "
                    + QuestionFollow.COLUMN_QUESTION_ID
                    + " = ?";
        }
        try {
            preparedStatement = DBConnection.getInstance().getConnection().prepareStatement(sql_query);
            if (type == QuestionFollowFacade.DELETE_BY_QUESTION_ID) {
                preparedStatement.setInt(1, (int) key.get(0));
            }
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(QuestionFollowFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
}
