/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.group_follow.GroupFollowConnection;
import dao.notification.NotificationConnection;
import dao.threads.ThreadConnection;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import model.Notification;
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
public class ThreadDetailCtr {

    AccountServiceFactory accountServiceFactory = new AccountServiceFactory();
    AccountServiceInterface accountServiceInterface = accountServiceFactory.getAccountService(
            AccountServiceFactory.USER_ACCOUNT_SERVICE
    );
    
    @RequestMapping(value = "/threads/{thread_id}/{title_raw:.+}")
    public String threadDetail(
            HttpServletRequest request, HttpSession session,
            @PathVariable(value = "thread_id") String thread_id,
            @PathVariable(value = "title_raw") String title_raw,
            ModelMap model
    ) {
        int t_id = Integer.parseInt(thread_id);
        ArrayList<Threads> ts = ThreadConnection.getThreadConnection().getById(t_id, title_raw);
        model.addAttribute("threads", ts.get(0));
        String checkNoti = request.getParameter("refe_noti");
        if (checkNoti != null) {
            int notify_id = Integer.parseInt(checkNoti);
            Notification n = new Notification();
            n.setId(notify_id);
            NotificationConnection.getNotificationConnection().changeStatus(n);
        }
        boolean isAdmin = false;
        User user = accountServiceInterface.checkLogin(session, request);
        if (user != null) {
            isAdmin = GroupFollowConnection.getAnswerConnection().checkAdmin(t_id, user.getIdUser());
        }
        
        model.addAttribute("isAdmin", isAdmin);
        
        return "view/frontend/templates/home/thread_detail";
    }
}
