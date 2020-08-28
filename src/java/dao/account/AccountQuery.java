/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.account;

import dao.user.UserConnection;
import dao.user_account.UserAccountConnection;
import java.util.ArrayList;
import model.Account;
import model.User;
import model.UserAccount;

/**
 *
 * @author nguye
 */
public class AccountQuery implements AccountQueryInterface {

    private AccountQueryFacade accountQueryFacade;

    public AccountQuery(AccountQueryFacade accountQueryFacade) {
        this.accountQueryFacade = accountQueryFacade;
    }

    /*
        ArrayList key :
            [0] username
            [1] password
     */
    @Override
    public User checkLogin(String username, String password) {
        User user = null;
        ArrayList<Object> key = new ArrayList<>();
        key.add(username);
        key.add(password);
        ArrayList<Account> account = this.accountQueryFacade.find(AccountQueryFacade.CHECK_LOGIN, key);
        if (account.isEmpty()) {
            return null;
        } else {
            UserAccount userAccount = UserAccountConnection.getUserAccountConnection().findByAccountId(account.get(0));
            user = UserConnection.getUserConnection().findByUserAccountId(userAccount.getIdUserAccount());
        }
        return user;
    }

    @Override
    public Account findById(int id) {
        ArrayList<Object> key = new ArrayList<>();
        key.add(id);
        return this.accountQueryFacade.find(AccountQueryFacade.FIND_BY_ID, key).get(0);
    }    

    @Override
    public int createNewAccount(Account a) {
        return this.accountQueryFacade.update(AccountQueryFacade.CREATE_NEW, a);
    }

    @Override
    public int updateEmail(Account a) {
        return accountQueryFacade.update(AccountQueryFacade.UPDATE_EMAIL, a);
    }

    @Override
    public Account findByEmail(String email) {
        ArrayList<Object> key = new ArrayList<>();
        key.add(email);
        return accountQueryFacade.find(AccountQueryFacade.FIND_BY_EMAIL, key).get(0);
    }

    @Override
    public int updatePassword(Account a) {
        return accountQueryFacade.update(AccountQueryFacade.UPDATE_PASSWORD, a);
    }

    @Override
    public ArrayList<Account> checkUsername(String username) {
        ArrayList<Object> key = new ArrayList<>();
        key.add(username);
        return accountQueryFacade.find(AccountQueryFacade.CHECK_USERNAME, key);
    }

    @Override
    public ArrayList<Account> checkEmail(String email) {
        ArrayList<Object> key = new ArrayList<>();
        key.add(email);
        return accountQueryFacade.find(AccountQueryFacade.CHECK_EMAIL, key);
    }
}
