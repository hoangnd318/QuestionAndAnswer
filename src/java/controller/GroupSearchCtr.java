/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.group.GroupConnection;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import model.Group;
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
public class GroupSearchCtr {
    
    AccountServiceFactory accountServiceFactory = new AccountServiceFactory();
    AccountServiceInterface accountServiceInterface = accountServiceFactory.getAccountService(
            AccountServiceFactory.USER_ACCOUNT_SERVICE
    );
    
    @RequestMapping(value = "groups/search")
    public String groupsSearch(HttpServletRequest request, ModelMap model, HttpSession session) {
        User user = accountServiceInterface.checkLogin(session, request);
        int u_id = -1;
        if (user != null) {
            u_id = user.getIdUser();
        }
        String key = request.getParameter("key");
        ArrayList<Group> groups = GroupConnection.getGroupConnection().getGroupByName(key, u_id);
        model.addAttribute("groups", groups);
        return "view/frontend/templates/home/group_search";
    }
    
}
