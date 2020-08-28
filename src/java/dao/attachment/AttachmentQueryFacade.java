/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.attachment;

import dao.dbconnection.DBConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Attachment;

/**
 *
 * @author nguye
 */
public class AttachmentQueryFacade {
    private PreparedStatement preparedStatement;

    public AttachmentQueryFacade() {
        this.preparedStatement = null;
    }
    
    public Attachment find(int id){
        Attachment attachment = new Attachment();
        String sql_query = "";
        sql_query = "SELECT * FROM " + Attachment.TABLE_NAME + " WHERE " + Attachment.COLUMN_ID + " = ?";
        try {
            preparedStatement = DBConnection.getInstance().getConnection().prepareStatement(sql_query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                attachment.setId(resultSet.getInt(Attachment.COLUMN_ID));
                attachment.setUrl(resultSet.getString(Attachment.COLUMN_URL));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AttachmentQueryFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return attachment;
    }
}
