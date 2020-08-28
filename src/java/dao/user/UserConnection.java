/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.user;

/**
 *
 * @author nguye
 */
public class UserConnection {
    
    private static UserQuery instance = null;
    
    public static UserQueryInterface getUserConnection(){
        if (instance == null) {
            instance = new UserQuery(new UserQueryFacade());
        }
        return instance;
    }
}
