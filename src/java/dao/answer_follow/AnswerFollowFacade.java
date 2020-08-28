/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.answer_follow;

import dao.dbconnection.DBConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.AnswerFollow;
import model.QuestionFollow;

/**
 *
 * @author nguye
 */
public class AnswerFollowFacade {

    public static int ADD_NEW_FOLLOW = 1;
    public static int UPDATE_FOLLOW = 2;
    
    public static int DELETE_BY_ANSWER_ID = 1;

    private PreparedStatement preparedStatement;

    public AnswerFollowFacade() {
        this.preparedStatement = null;
    }

    public String get(int answer_id) {
        String follow = "";
        String sql_query = "SELECT a.user_id FROM answer_follow a WHERE a.answer_id=?";
        try {
            preparedStatement = DBConnection.getInstance().getConnection().prepareStatement(sql_query);
            preparedStatement.setInt(1, answer_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                follow += resultSet.getInt(AnswerFollow.COLUMN_USER_ID) + ",";
            }
        } catch (SQLException ex) {
            Logger.getLogger(AnswerFollowFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return follow;
    }
    
    public boolean update(int type, int answer_id, int user_id, String role) {
        String sql_query = null;
        if (type == AnswerFollowFacade.ADD_NEW_FOLLOW) {
            sql_query = "INSERT INTO "
                    + AnswerFollow.TABLE_NAME
                    + "(" + AnswerFollow.COLUMN_ROLE + ","
                    + AnswerFollow.COLUMN_ANSWER_ID + ","
                    + AnswerFollow.COLUMN_USER_ID + ") VALUES(?,?,?)";
        }

        try {
            preparedStatement = DBConnection.getInstance().getConnection().prepareStatement(sql_query);
            if (type == AnswerFollowFacade.ADD_NEW_FOLLOW) {
                preparedStatement.setString(1, role);
                preparedStatement.setInt(2, answer_id);
                preparedStatement.setInt(3, user_id);
            }
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AnswerFollowFacade.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    public boolean checkFollowed(int answer_id, int user_id) {
        String sql_query = "SELECT * FROM "
                + AnswerFollow.TABLE_NAME
                + " WHERE "
                + AnswerFollow.COLUMN_USER_ID
                + " = ? && "
                + AnswerFollow.COLUMN_ANSWER_ID
                + " = ?";
        try {
            preparedStatement = DBConnection.getInstance().getConnection().prepareStatement(sql_query);
            preparedStatement.setInt(1, user_id);
            preparedStatement.setInt(2, answer_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AnswerFollowFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public boolean delete(int type, ArrayList<Object> key){
        String sql_query = "";
        if (type == AnswerFollowFacade.DELETE_BY_ANSWER_ID) {
            sql_query = "DELETE FROM "
                    + AnswerFollow.TABLE_NAME
                    + " WHERE "
                    + AnswerFollow.COLUMN_ANSWER_ID
                    + " = ?";
        }
        try {
            preparedStatement = DBConnection.getInstance().getConnection().prepareStatement(sql_query);
            if (type == AnswerFollowFacade.DELETE_BY_ANSWER_ID) {
                preparedStatement.setInt(1, (int)key.get(0));
            }
            System.out.println(sql_query);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AnswerFollowFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
}
