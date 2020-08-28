/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.google.gson.Gson;
import dao.notification.NotificationConnection;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import model.Notification;
import model.ResponseRequest;
import model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import service.AccountServiceFactory;
import service.AccountServiceInterface;

/**
 *
 * @author nguye
 */
//ke thua Controller
//requestmapping la thuoc tinh
@Controller
@RequestMapping(value = "qanda", method = RequestMethod.GET)
public class NotificationsApiCtr {
    
    AccountServiceFactory accountServiceFactory = new AccountServiceFactory();
    AccountServiceInterface accountServiceInterface = accountServiceFactory.getAccountService(
            AccountServiceFactory.USER_ACCOUNT_SERVICE
    );
    
    //lay thong bao
    @RequestMapping(value = "getNotifications", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String getNotifications(HttpServletRequest request, HttpSession session) {
        session = request.getSession();
        User user = accountServiceInterface.checkLogin(session, request);
        ArrayList<Notification> notifications = NotificationConnection.getNotificationConnection().findById(user.getIdUser());
        NotificationConnection.getNotificationConnection().markSeen(user.getIdUser());
        String result = new Gson().toJson(notifications);
        return result;
    }

    //lay thong bao tiep theo
    @RequestMapping(value = "getNotifyNext", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String getNotifyNext(HttpServletRequest request, HttpSession session) {
        User user = accountServiceInterface.checkLogin(session, request);
        ArrayList<Notification> notifications = null;
        if (user != null) {
            int n_id = Integer.parseInt(request.getParameter("n_id"));
            notifications = NotificationConnection.getNotificationConnection().findByIdScroll(user.getIdUser(), n_id);
            System.out.println(notifications.size());
        }
        String result = new Gson().toJson(notifications);
        return result;
    }

    //lay so luong thong bao
    @RequestMapping(value = "getCountNotifications", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String getCountNotifications(HttpServletRequest request, HttpSession session) {
        session = request.getSession();
        User user = accountServiceInterface.checkLogin(session, request);
        Integer count = NotificationConnection.getNotificationConnection().getCountUnseenNotification(user.getIdUser());
        ResponseRequest responseRequest = new ResponseRequest(ResponseRequest.RESPONSE_ACCESS, count.toString());
        String result = new Gson().toJson(responseRequest);
        return result;
    }
}
