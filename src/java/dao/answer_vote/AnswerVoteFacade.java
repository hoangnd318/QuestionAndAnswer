/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.answer_vote;

import dao.answer.AnswerConnection;
import dao.dbconnection.DBConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.AnswerVote;
import model.QuestionVote;

/**
 *
 * @author nguye
 */
public class AnswerVoteFacade {

    public static int VOTE_ANSWER = 1;
    public static int UNVOTE_ANSWER = 2;
    public static int CHANGE_VOTE_ANSWER = 3;
    
    public static int DELETE_BY_ANSWER_ID = 1;

    private PreparedStatement preparedStatement;

    public AnswerVoteFacade() {
        this.preparedStatement = null;
    }

    public int getVote(int id) {
        int voteUp = 0, voteDown = 0;
        String sql_query = "SELECT * FROM "
                + AnswerVote.TABLE_NAME
                + " WHERE "
                + AnswerVote.COLUMN_ANSWER_ID + " = ?";
        try {
            preparedStatement = DBConnection.getInstance().getConnection().prepareStatement(sql_query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                if (resultSet.getString(AnswerVote.COLUMN_TYPE).compareTo(AnswerVote.VOTE_UP_TYPE) == 0) {
                    voteUp++;
                } else if (resultSet.getString(AnswerVote.COLUMN_TYPE).compareTo(AnswerVote.VOTE_DOWN_TYPE) == 0) {
                    voteDown++;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(AnswerVoteFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (voteUp - voteDown);
    }

    public String checkVoted(int answer_id, int user_id) {
        String result = "un_vote";
        String sql_query = "SELECT * FROM "
                + AnswerVote.TABLE_NAME
                + " WHERE "
                + AnswerVote.COLUMN_USER_ID + " = ? && "
                + AnswerVote.COLUMN_ANSWER_ID + " = ?";
        try {
            preparedStatement = DBConnection.getInstance().getConnection().prepareStatement(sql_query);
            preparedStatement.setInt(1, user_id);
            preparedStatement.setInt(2, answer_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result = resultSet.getString(QuestionVote.COLUMN_TYPE);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AnswerVoteFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public boolean vote(int type, String typeVote, int a_id, int u_id) {
        String sql_query = null;

        if (type == AnswerVoteFacade.VOTE_ANSWER) {
            sql_query = "INSERT INTO "
                    + AnswerVote.TABLE_NAME
                    + "(" + AnswerVote.COLUMN_TYPE + ","
                    + AnswerVote.COLUMN_USER_ID + ","
                    + AnswerVote.COLUMN_ANSWER_ID + ") VALUES(?,?,?)";
        } else if (type == AnswerVoteFacade.UNVOTE_ANSWER) {
            sql_query = "DELETE FROM "
                    + AnswerVote.TABLE_NAME
                    + " WHERE "
                    + AnswerVote.COLUMN_USER_ID + " = ? && "
                    + AnswerVote.COLUMN_ANSWER_ID + " = ?";
        } else if (type == AnswerVoteFacade.CHANGE_VOTE_ANSWER) {
            sql_query = "UPDATE "
                    + AnswerVote.TABLE_NAME
                    + " SET "
                    + AnswerVote.COLUMN_TYPE + " = ?"
                    + " WHERE "
                    + AnswerVote.COLUMN_USER_ID + " = ? && "
                    + AnswerVote.COLUMN_ANSWER_ID + " = ?";
        }
        try {
            preparedStatement = DBConnection.getInstance().getConnection().prepareStatement(sql_query);
            if (type == AnswerVoteFacade.VOTE_ANSWER || type == AnswerVoteFacade.CHANGE_VOTE_ANSWER) {
                preparedStatement.setString(1, typeVote);
                preparedStatement.setInt(2, u_id);
                preparedStatement.setInt(3, a_id);
            } else if(type == AnswerVoteFacade.UNVOTE_ANSWER){
                preparedStatement.setInt(1, u_id);
                preparedStatement.setInt(2, a_id);
            }
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AnswerVoteFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
    
    public boolean delete(int type, ArrayList<Object> key){
        String sql_query = "";
        if (type == AnswerVoteFacade.DELETE_BY_ANSWER_ID) {
            sql_query = "DELETE FROM "
                    + AnswerVote.TABLE_NAME
                    + " WHERE "
                    + AnswerVote.COLUMN_ANSWER_ID
                    + " = ?";
        }
        try {
            preparedStatement = DBConnection.getInstance().getConnection().prepareStatement(sql_query);
            if (type == AnswerVoteFacade.DELETE_BY_ANSWER_ID) {
                preparedStatement.setInt(1, (int)key.get(0));
            }
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AnswerVoteFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
}
