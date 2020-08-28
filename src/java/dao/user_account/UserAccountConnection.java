/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.user_account;

/**
 *
 * @author nguye
 */
public class UserAccountConnection {
    
    private static UserAccountQuery instance = null;
    
    public static UserAccountQueryInterface getUserAccountConnection(){
        if (instance == null) {
            instance = new UserAccountQuery(new UserAccountQueryFacade());
        }
        return instance;
    }
}
