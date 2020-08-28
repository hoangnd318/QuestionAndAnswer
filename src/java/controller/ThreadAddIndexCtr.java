/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.threads.ThreadConnection;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import model.Threads;
import model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
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
public class ThreadAddIndexCtr {
    
    AccountServiceFactory accountServiceFactory = new AccountServiceFactory();
    AccountServiceInterface accountServiceInterface = accountServiceFactory.getAccountService(
            AccountServiceFactory.USER_ACCOUNT_SERVICE
    );
    
    //them 
    @RequestMapping(value = "/groups/{id}/threads/add")
    public String threadAdd(HttpServletRequest request, ModelMap model, @PathVariable(value = "id") String group_id) {
        HttpSession session = request.getSession();
        User user = accountServiceInterface.checkLogin(session, request);
        if (user == null) {
            return "redirect:" + "/";
        }
        int g_id = Integer.parseInt(group_id);
        model.addAttribute("group_id", g_id);
        return "view/frontend/templates/home/thread_add";
    }
    
    //chinh sua
    @RequestMapping(value = "/threads/edit/{id}")
    public String threadEdit(
            HttpServletRequest request, HttpSession session,
            @PathVariable(value = "id") String thread_id,
            ModelMap model
    ) {
        int int_thread_id = Integer.parseInt(thread_id);
        ArrayList<Threads> t_tmp = ThreadConnection.getThreadConnection().getByOnlyId(int_thread_id);
        Threads thread = null;
        if (t_tmp.size() != 0) {
            thread = t_tmp.get(0);
        } else {
            return "view/frontend/templates/home/error";
        }
        User user = accountServiceInterface.checkLogin(session, request);
        if (user == null || user.getIdUser() != thread.getAuthor().getIdUser()) {
            return "view/frontend/templates/home/error";
        }
        model.addAttribute(
                "threads",
                thread
        );
        return "view/frontend/templates/home/thread_add";
    }
}
