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
@RequestMapping(value = "", method = RequestMethod.GET)
public class GroupListCtr {
    
    AccountServiceFactory accountServiceFactory = new AccountServiceFactory();
    AccountServiceInterface accountServiceInterface = accountServiceFactory.getAccountService(
            AccountServiceFactory.USER_ACCOUNT_SERVICE
    );
    
    @RequestMapping(value = "groups/list")
    public String groupsList(HttpServletRequest request, ModelMap model, HttpSession session) {
        User user = accountServiceInterface.checkLogin(session, request);
        int u_id = -1;
        if (user != null) {
            u_id = user.getIdUser();
        } else {
            return "view/frontend/templates/home/error";
        }
        //
        model.addAttribute("groups_admin", GroupConnection.getGroupConnection().getGroupAdmin(u_id));
        //
        model.addAttribute("groups_non_admin", GroupConnection.getGroupConnection().getGroupNoneAdmin(u_id));
        return "view/frontend/templates/home/group_list";
    }
}
