/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.user_account;

import model.Account;
import model.UserAccount;

/**
 *
 * @author nguye
 */
public interface UserAccountQueryInterface {
    public UserAccount findByAccountId(Account account);
    public UserAccount findById(int id);
    public int createNewUserAccount(UserAccount ua);
}
