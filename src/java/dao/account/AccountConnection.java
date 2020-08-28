/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.account;

/**
 *
 * @author nguye
 */
public class AccountConnection {
    private static AccountQuery instance = null;
    
    public static AccountQueryInterface getAccountConnection(){
        if(instance == null){
            instance = new AccountQuery(new AccountQueryFacade());
        }
        return instance;
    }
}
