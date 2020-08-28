/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.account_avatar;

import dao.attachment.AttachmentConnection;
import dao.dbconnection.DBConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.AccountAvatar;

/**
 *
 * @author nguye
 */
public class AccountAvatarQueryFacade {
    private PreparedStatement preparedStatement;

    public AccountAvatarQueryFacade() {
        this.preparedStatement = null;
    }
    
    public AccountAvatar find(int id){
        AccountAvatar accountAvatar = new AccountAvatar();
        String sql_query = "";
        sql_query = "SELECT * FROM " + AccountAvatar.TABLE_NAME + " WHERE " + AccountAvatar.COLUMN_ID + " = ?";
        try {
            preparedStatement = DBConnection.getInstance().getConnection().prepareStatement(sql_query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                accountAvatar.setIdAccountAvatar(resultSet.getInt(AccountAvatar.COLUMN_ID_ACCOUNT_AVATAR));
                accountAvatar.setUrl(AttachmentConnection.getAttachmentConnection().findById(resultSet.getInt(AccountAvatar.COLUMN_ATTACHMENT_ID)).getUrl());
                accountAvatar.setId(resultSet.getInt(AccountAvatar.COLUMN_ATTACHMENT_ID));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountAvatarQueryFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return accountAvatar;
    }
}
