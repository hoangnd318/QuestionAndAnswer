/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.account;

import java.util.ArrayList;
import model.Account;
import model.User;


/**
 *
 * @author nguye
 */
public interface AccountQueryInterface {
    public User checkLogin(String username, String password);
    public Account findById(int id);
    public Account findByEmail(String email);
    public ArrayList<Account> checkUsername(String username);
    public ArrayList<Account> checkEmail(String email);
    public int createNewAccount(Account a);
    public int updateEmail(Account a);
    public int updatePassword(Account a);
}
