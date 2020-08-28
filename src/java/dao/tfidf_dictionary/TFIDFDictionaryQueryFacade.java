/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.tfidf_dictionary;

import dao.dbconnection.DBConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nguye
 */
public class TFIDFDictionaryQueryFacade {

    private PreparedStatement preparedStatement;

    public TFIDFDictionaryQueryFacade() {
        this.preparedStatement = null;
    }

    public List<String> get(){
        List<String> docs = new ArrayList<>();
        String sql_query = "SELECT * FROM tfidf_dictionary ORDER BY doc ASC";
        try {
            preparedStatement = DBConnection.getInstance().getConnection().prepareStatement(sql_query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                docs.add(resultSet.getString("doc"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(TFIDFDictionaryQueryFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return docs;
    }
    
    public boolean check(String doc) {
        String sql_query = "SELECT * FROM tfidf_dictionary a WHERE a.doc=?";
        try {
            preparedStatement = DBConnection.getInstance().getConnection().prepareStatement(sql_query);
            preparedStatement.setString(1, doc);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(TFIDFDictionaryQueryFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean update(String doc) {
        String sql_query = "INSERT INTO tfidf_dictionary(doc) VALUES(?)";
        try {
            preparedStatement = DBConnection
                    .getInstance()
                    .getConnection()
                    .prepareStatement(sql_query);
            preparedStatement.setString(1, doc);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(TFIDFDictionaryQueryFacade.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }
}
