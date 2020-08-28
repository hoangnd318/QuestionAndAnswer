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
public class UserAccountService implements AccountServiceInterface {

    @Override
    public void login(User user, HttpSession session, HttpServletRequest request) {
        session.setAttribute(AccountServiceFactory.SESSION_USER_ACCOUNT, user);
    }

    @Override
    public void logout(User user, HttpSession session, HttpServletRequest request) {
        session.removeAttribute(AccountServiceFactory.SESSION_USER_ACCOUNT);
    }

    @Override
    public User checkLogin(HttpSession session, HttpServletRequest request) {
        User user = null;
        user = (User) session.getAttribute(AccountServiceFactory.SESSION_USER_ACCOUNT);
        if (user == null) {
            return null;
        } else {
            return user;
        }
    }

    @Override
    public boolean checkPermission(HttpSession session, HttpServletRequest request, User user) {
        User userCurrent = null;
        userCurrent = (User) session.getAttribute(AccountServiceFactory.SESSION_USER_ACCOUNT);
        if(userCurrent == null){
            return false;
        }
        if(userCurrent.getIdUser() != user.getIdUser()){
            return false;
        }
        return true;
    }

}
