/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.question_vote;

import dao.dbconnection.DBConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.QuestionVote;

/**
 *
 * @author nguye
 */
public class QuestionVoteFacade {

    public static int VOTE_QUESTION = 1;
    public static int UNVOTE_QUESTION = 2;
    public static int CHANGE_VOTE_QUESTION = 3;

    public static int DELETE_BY_QUESTION_ID = 1;

    private PreparedStatement preparedStatement;

    public QuestionVoteFacade() {
        this.preparedStatement = null;
    }

    public int getVote(int id) {
        int voteUp = 0, voteDown = 0;
        String sql_query = "SELECT * FROM " + QuestionVote.TABLE_NAME + " WHERE " + QuestionVote.COLUMN_QUESTION_ID + " = ?";
        try {
            preparedStatement = DBConnection.getInstance().getConnection().prepareStatement(sql_query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                if (resultSet.getString(QuestionVote.COLUMN_TYPE).compareTo(QuestionVote.VOTE_UP_TYPE) == 0) {
                    voteUp++;
                } else if (resultSet.getString(QuestionVote.COLUMN_TYPE).compareTo(QuestionVote.VOTE_DOWN_TYPE) == 0) {
                    voteDown++;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuestionVoteFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (voteUp - voteDown);
    }

    public String checkVoted(int question_id, int user_id) {
        String result = "un_vote";
        String sql_query = "SELECT * FROM "
                + QuestionVote.TABLE_NAME
                + " WHERE "
                + QuestionVote.COLUMN_USER_ID + " = ? && "
                + QuestionVote.COLUMN_QUESTION_ID + " = ?";
        try {
            preparedStatement = DBConnection.getInstance().getConnection().prepareStatement(sql_query);
            preparedStatement.setInt(1, user_id);
            preparedStatement.setInt(2, question_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result = resultSet.getString(QuestionVote.COLUMN_TYPE);
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuestionVoteFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public boolean vote(int type, String typeVote, int q_id, int u_id) {
        String sql_query = null;

        if (type == QuestionVoteFacade.VOTE_QUESTION) {
            sql_query = "INSERT INTO "
                    + QuestionVote.TABLE_NAME
                    + "(" + QuestionVote.COLUMN_TYPE + ","
                    + QuestionVote.COLUMN_USER_ID + ","
                    + QuestionVote.COLUMN_QUESTION_ID + ") VALUES(?,?,?)";
        } else if (type == QuestionVoteFacade.UNVOTE_QUESTION) {
            sql_query = "DELETE FROM "
                    + QuestionVote.TABLE_NAME
                    + " WHERE "
                    + QuestionVote.COLUMN_USER_ID + " = ? && "
                    + QuestionVote.COLUMN_QUESTION_ID + " = ?";
        } else if (type == QuestionVoteFacade.CHANGE_VOTE_QUESTION) {
            sql_query = "UPDATE "
                    + QuestionVote.TABLE_NAME
                    + " SET "
                    + QuestionVote.COLUMN_TYPE + " = ?"
                    + " WHERE "
                    + QuestionVote.COLUMN_USER_ID + " = ? && "
                    + QuestionVote.COLUMN_QUESTION_ID + " = ?";
        }
        try {
            preparedStatement = DBConnection.getInstance().getConnection().prepareStatement(sql_query);
            if (type == QuestionVoteFacade.VOTE_QUESTION || type == QuestionVoteFacade.CHANGE_VOTE_QUESTION) {
                preparedStatement.setString(1, typeVote);
                preparedStatement.setInt(2, u_id);
                preparedStatement.setInt(3, q_id);
            } else if (type == QuestionVoteFacade.UNVOTE_QUESTION) {
                preparedStatement.setInt(1, u_id);
                preparedStatement.setInt(2, q_id);
            }
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(QuestionVoteFacade.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    public boolean delete(int type, ArrayList<Object> key) {
        String sql_query = "";
        if (type == QuestionVoteFacade.DELETE_BY_QUESTION_ID) {
            sql_query = "DELETE FROM "
                    + QuestionVote.TABLE_NAME
                    + " WHERE "
                    + QuestionVote.COLUMN_QUESTION_ID
                    + " = ?";
        }
        try {
            preparedStatement = DBConnection.getInstance().getConnection().prepareStatement(sql_query);
            if (type == QuestionVoteFacade.DELETE_BY_QUESTION_ID) {
                preparedStatement.setInt(1, (int)key.get(0));
            }
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(QuestionVoteFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
}
