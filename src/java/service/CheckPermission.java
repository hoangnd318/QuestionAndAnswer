/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import model.User;

/**
 *
 * @author nguye
 */
public class CheckPermission {

    AccountServiceFactory accountServiceFactory = new AccountServiceFactory();
    AccountServiceInterface accountServiceInterface = accountServiceFactory.getAccountService(
            AccountServiceFactory.USER_ACCOUNT_SERVICE
    );

    public boolean checkPermission(User user, HttpSession session, HttpServletRequest request) {
        User user_login = accountServiceInterface.checkLogin(session, request);
        if (user_login == null) {
            return false;
        }
        if (user != null) {
            if (user_login.getIdUser() != user.getIdUser()) {
                return false;
            }
        }
        return true;
    }
}
