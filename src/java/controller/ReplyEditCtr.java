/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.reply.ReplyConnection;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import model.Reply;
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
public class ReplyEditCtr {

    AccountServiceFactory accountServiceFactory = new AccountServiceFactory();
    AccountServiceInterface accountServiceInterface = accountServiceFactory.getAccountService(
            AccountServiceFactory.USER_ACCOUNT_SERVICE
    );

    //chinh sua
    @RequestMapping(value = "/reply/edit/{id}")
    public String questionEdit(
            HttpServletRequest request, HttpSession session,
            @PathVariable(value = "id") String reply_id,
            ModelMap model
    ) {
        int int_reply_id = Integer.parseInt(reply_id);
        ArrayList<Reply> r_tmp = ReplyConnection.getReplyConnection().getById(int_reply_id);
        Reply reply = null;
        if (r_tmp.size() != 0) {
            reply = r_tmp.get(0);
        } else {
            return "view/frontend/templates/home/error";
        }
        User user = accountServiceInterface.checkLogin(session, request);
        if (user == null || user.getIdUser() != reply.getAuthor().getIdUser()) {
            return "view/frontend/templates/home/error";
        }
        model.addAttribute(
                "reply",
                reply
        );
        return "view/frontend/templates/home/reply_edit";
    }
}
