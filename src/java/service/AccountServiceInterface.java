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
public interface AccountServiceInterface {
    public void login(User user, HttpSession session, HttpServletRequest request);
    public void logout(User user, HttpSession session, HttpServletRequest request);
    public User checkLogin(HttpSession session, HttpServletRequest request);
    public boolean checkPermission(HttpSession session, HttpServletRequest request, User user);
}
