/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.user_account;

import java.util.ArrayList;
import model.Account;
import model.UserAccount;

/**
 *
 * @author nguye
 */
public class UserAccountQuery implements UserAccountQueryInterface{
    private UserAccountQueryFacade userAccountQueryFacade;
    
    public UserAccountQuery(UserAccountQueryFacade userAccountQueryFacade){
        this.userAccountQueryFacade = userAccountQueryFacade;
    }
    
    @Override
    public UserAccount findByAccountId(Account account) {
        return this.userAccountQueryFacade.find(UserAccountQueryFacade.FIND_BY_ACCOUNT_ID, null, account).get(0);
    }

    @Override
    public UserAccount findById(int id) {
        ArrayList<Object> key = new ArrayList<>();
        key.add(id);
        return this.userAccountQueryFacade.find(UserAccountQueryFacade.FIND_BY_ID, key, null).get(0);
    }

    @Override
    public int createNewUserAccount(UserAccount ua) {
        return this.userAccountQueryFacade.update(ua);
    }
    
}
