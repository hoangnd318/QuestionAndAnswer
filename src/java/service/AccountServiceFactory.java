/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

/**
 *
 * @author nguye
 */
public class AccountServiceFactory {

    public static int USER_ACCOUNT_SERVICE = 1;
    public static int ADMIN_ACCOUNT_SERVICE = 2;
    public static String SESSION_USER_ACCOUNT = "user_account";

    public AccountServiceInterface getAccountService(int type) {
        if(type == AccountServiceFactory.USER_ACCOUNT_SERVICE){
            return new UserAccountService();
        }else{
            return new AdminAccountService();
        }
    }
}
