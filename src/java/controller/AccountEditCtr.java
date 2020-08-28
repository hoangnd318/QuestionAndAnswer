/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.course.CourseConnection;
import dao.department.DepartmentConnection;
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
@RequestMapping(value = "account", method = RequestMethod.GET)
public class AccountEditCtr {
        
    AccountServiceFactory accountServiceFactory = new AccountServiceFactory();
    AccountServiceInterface accountServiceInterface = accountServiceFactory.getAccountService(
            AccountServiceFactory.USER_ACCOUNT_SERVICE
    );
    
    @RequestMapping(value = "edit", method = RequestMethod.GET)
    public String edit(HttpServletRequest request, ModelMap model, HttpSession session) {
        User user = accountServiceInterface.checkLogin(session, request);
        if (user == null) {
            return "view/frontend/templates/home/error";
        }
        model.addAttribute("khoa", CourseConnection.getCourseConnection().findAll());
        model.addAttribute("nganh", DepartmentConnection.getDepartmentConnection().findAll());
        return "view/frontend/templates/home/account/register_account";
    }
}
