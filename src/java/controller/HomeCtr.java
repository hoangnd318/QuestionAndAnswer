/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.group.GroupConnection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import service.AccountServiceFactory;
import service.AccountServiceInterface;

/**
 *
 * @author nguye
 */
@Controller
public class HomeCtr {

    AccountServiceFactory accountServiceFactory = new AccountServiceFactory();
    AccountServiceInterface accountServiceInterface = accountServiceFactory.getAccountService(
            AccountServiceFactory.USER_ACCOUNT_SERVICE
    );

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(
            HttpServletRequest request,
            ModelMap model,
            HttpSession session) {
        //get user dang nhap
        User user = accountServiceInterface.checkLogin(session, request);
        if (user != null) {
            model.addAttribute("group_private", GroupConnection.getGroupConnection().getGroupByUserId(user.getIdUser()));
        }

        //gan nhom public
        model.addAttribute("group_default", GroupConnection.getGroupConnection().getGroupDefault());
        return "view/frontend/templates/home/home";
    }
}
