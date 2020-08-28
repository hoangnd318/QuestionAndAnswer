/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.account;

import dao.account_avatar.AccountAvatarConnection;
import dao.dbconnection.DBConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;
import model.AccountAvatar;

/**
 *
 * @author nguye
 */
public class AccountQueryFacade {

    public static int CHECK_LOGIN = 1;
    public static int FIND_BY_ID = 2;
    public static int FIND_BY_EMAIL = 3;
    public static int CHECK_USERNAME = 4;
    public static int CHECK_EMAIL = 5;

    public static int CREATE_NEW = 1;
    public static int UPDATE_EMAIL = 2;
    public static int UPDATE_PASSWORD = 3;

    private PreparedStatement preparedStatement;

    public AccountQueryFacade() {
        this.preparedStatement = null;
    }

    public ArrayList<Account> find(int type, ArrayList<Object> key) {
        ArrayList<Account> accounts = new ArrayList<>();
        String sql_query = "";
        if (type == AccountQueryFacade.CHECK_LOGIN) {
            sql_query = "SELECT * FROM " + Account.TABLE_NAME + " WHERE " + Account.COLUMN_USERNAME + " = ? && " + Account.COLUMN_PASSWORD + " = ?";
        } else if (type == AccountQueryFacade.FIND_BY_ID) {
            sql_query = "SELECT * FROM " + Account.TABLE_NAME + " WHERE " + Account.COLUMN_ID + " = ?";
        } else if (type == AccountQueryFacade.FIND_BY_EMAIL) {
            sql_query = "SELECT * FROM account WHERE email = ?";
        } else if (type == AccountQueryFacade.CHECK_USERNAME) {
            sql_query = "SELECT * FROM account WHERE username = ?";
        } else if (type == AccountQueryFacade.CHECK_EMAIL) {
            sql_query = "SELECT * FROM account WHERE email = ?";
        }
        try {
            preparedStatement = DBConnection.getInstance().getConnection().prepareStatement(sql_query);
            /*
                if type == 1 -> key:
                    [0] username
                    [1] password
             */
            if (type == AccountQueryFacade.CHECK_LOGIN) {
                preparedStatement.setString(1, (String) key.get(0));
                preparedStatement.setString(2, (String) key.get(1));
            } else if (type == AccountQueryFacade.FIND_BY_ID) {
                preparedStatement.setInt(1, (int) key.get(0));
            } else if (type == AccountQueryFacade.FIND_BY_EMAIL) {
                preparedStatement.setString(1, (String) key.get(0));
            } else if (type == AccountQueryFacade.CHECK_USERNAME){
                preparedStatement.setString(1, (String) key.get(0));
            } else if (type == AccountQueryFacade.CHECK_EMAIL){
                preparedStatement.setString(1, (String) key.get(0));
            }
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Account account = new Account();
                account.setId(resultSet.getInt(Account.COLUMN_ID));
                account.setUsername(resultSet.getString(Account.COLUMN_USERNAME));
                account.setPassword(resultSet.getString(Account.COLUMN_PASSWORD));
                account.setEmail(resultSet.getString(Account.COLUMN_EMAIL));
                AccountAvatar accountAvatar = new AccountAvatar();
                if (resultSet.getInt(Account.COLUMN_ACCOUNT_AVATAR_ID) == 0) {
                    accountAvatar.setIdAccountAvatar(0);
                    accountAvatar.setId(0);
                    accountAvatar.setUrl(AccountAvatar.NO_AVATAR_URL);
                } else {
                    accountAvatar = AccountAvatarConnection.getAccountAvatarConnection().findById(resultSet.getInt(Account.COLUMN_ACCOUNT_AVATAR_ID));
                }
                account.setAvatar(accountAvatar);
                accounts.add(account);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountQueryFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return accounts;
    }

    public int update(int type, Account a) {
        int account_id = 0;
        String sql_query = "";
        if (type == AccountQueryFacade.CREATE_NEW) {
            if (a.getAvatar() != null) {
                sql_query = "INSERT INTO "
                        + Account.TABLE_NAME
                        + "(" + Account.COLUMN_EMAIL + ","
                        + Account.COLUMN_USERNAME + ","
                        + Account.COLUMN_PASSWORD + ","
                        + Account.COLUMN_ACCOUNT_AVATAR_ID + ") VALUES(?,?,?,?)";
            } else {
                sql_query = "INSERT INTO "
                        + Account.TABLE_NAME
                        + "(" + Account.COLUMN_EMAIL + ","
                        + Account.COLUMN_USERNAME + ","
                        + Account.COLUMN_PASSWORD + ") VALUES(?,?,?)";
            }
        } else if (type == AccountQueryFacade.UPDATE_EMAIL) {
            sql_query = "UPDATE account SET email=? WHERE id=?";
        } else if (type == AccountQueryFacade.UPDATE_PASSWORD) {
            sql_query = "UPDATE account a SET a.password=? WHERE a.id=?";
        }
        try {
            preparedStatement = DBConnection.getInstance().getConnection().prepareStatement(sql_query, Statement.RETURN_GENERATED_KEYS);
            if (type == AccountQueryFacade.CREATE_NEW) {
                if (a.getAvatar() != null) {
                    preparedStatement.setString(1, a.getEmail());
                    preparedStatement.setString(2, a.getUsername());
                    preparedStatement.setString(3, a.getPassword());
                    preparedStatement.setInt(4, a.getAvatar().getIdAccountAvatar());
                } else {
                    preparedStatement.setString(1, a.getEmail());
                    preparedStatement.setString(2, a.getUsername());
                    preparedStatement.setString(3, a.getPassword());
                }
                preparedStatement.executeUpdate();
                ResultSet rs = preparedStatement.getGeneratedKeys();
                if (rs.next()) {
                    account_id = rs.getInt(1);
                    a.setId(account_id);
                }
            } else if (type == AccountQueryFacade.UPDATE_EMAIL) {
                preparedStatement.setString(1, a.getEmail());
                preparedStatement.setInt(2, a.getId());
                preparedStatement.executeUpdate();
            } else if (type == AccountQueryFacade.UPDATE_PASSWORD) {
                preparedStatement.setString(1, a.getPassword());
                preparedStatement.setInt(2, a.getId());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountQueryFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return account_id;
    }
}
