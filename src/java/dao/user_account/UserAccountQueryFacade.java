/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.user_account;

import dao.account.AccountConnection;
import dao.dbconnection.DBConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;
import model.UserAccount;

/**
 *
 * @author nguye
 */
public class UserAccountQueryFacade {

    public static int FIND_BY_ACCOUNT_ID = 1;
    public static int FIND_BY_ID = 2;

    private PreparedStatement preparedStatement;

    public UserAccountQueryFacade() {
        this.preparedStatement = null;
    }

    public ArrayList<UserAccount> find(int type, ArrayList<Object> key, Account account) {
        ArrayList<UserAccount> userAccounts = new ArrayList<>();
        String sql_query = "";
        if (type == UserAccountQueryFacade.FIND_BY_ACCOUNT_ID) {
            sql_query = "SELECT * FROM " + UserAccount.TABLE_NAME + " WHERE " + UserAccount.COLUMN_ACCOUNT_ID + " = ?";
        } else if (type == UserAccountQueryFacade.FIND_BY_ID) {
            sql_query = "SELECT * FROM " + UserAccount.TABLE_NAME + " WHERE " + UserAccount.COLUMN_ID_USER_ACCOUNT + " = ?";
        }
        try {
            preparedStatement = DBConnection.getInstance().getConnection().prepareStatement(sql_query);
            if (type == UserAccountQueryFacade.FIND_BY_ACCOUNT_ID) {
                preparedStatement.setInt(1, account.getId());
            } else if (type == UserAccountQueryFacade.FIND_BY_ID) {
                int id = (int) key.get(0);
                preparedStatement.setInt(1, id);
            }
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                UserAccount userAccount = new UserAccount();
                userAccount.setIdUserAccount(resultSet.getInt(UserAccount.COLUMN_ID_USER_ACCOUNT));
                if (type == UserAccountQueryFacade.FIND_BY_ACCOUNT_ID) {
                    userAccount.setUsername(account.getUsername());
                    userAccount.setPassword(account.getPassword());
                    userAccount.setEmail(account.getEmail());
                    userAccount.setAvatar(account.getAvatar());
                } else if (type == UserAccountQueryFacade.FIND_BY_ID) {
                    Account accountTmp = AccountConnection.getAccountConnection().findById(resultSet.getInt(UserAccount.COLUMN_ACCOUNT_ID));
                    userAccount.setUsername(accountTmp.getUsername());
                    userAccount.setPassword(accountTmp.getPassword());
                    userAccount.setEmail(accountTmp.getEmail());
                    userAccount.setAvatar(accountTmp.getAvatar());
                }
                userAccounts.add(userAccount);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserAccountQueryFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return userAccounts;
    }

    public int update(UserAccount ua) {
        int user_account_id = 0;
        String sql_query = "INSERT INTO "
                + UserAccount.TABLE_NAME
                + "("
                + UserAccount.COLUMN_ACCOUNT_ID
                + ") VALUES(?)";
        try {
            preparedStatement = DBConnection.getInstance().getConnection().prepareStatement(sql_query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, ua.getId());
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                user_account_id = rs.getInt(1);
                ua.setId(user_account_id);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserAccountQueryFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user_account_id;
    }

}
