/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.question_recommended;

import dao.dbconnection.DBConnection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nguye
 */
public class QuestionRecommendedQueryFacade {
    
    private PreparedStatement preparedStatement;

    public QuestionRecommendedQueryFacade() {
        this.preparedStatement = null;
    }
    
    public void update(int id_q_root, int id_q_recommend){
        String sql_query = "INSERT INTO question_recommended (q_root, q_recommended) VALUES (?,?)";
        try {
            preparedStatement = DBConnection.getInstance().getConnection().prepareStatement(sql_query);
            preparedStatement.setInt(1, id_q_root);
            preparedStatement.setInt(2, id_q_recommend);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(QuestionRecommendedQueryFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
