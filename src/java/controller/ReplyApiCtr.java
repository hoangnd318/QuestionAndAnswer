/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.google.gson.Gson;
import dao.reply.ReplyConnection;
import dao.thread_follow.ThreadFollowConnection;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import model.Reply;
import model.ResponseRequest;
import model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import service.AccountServiceFactory;
import service.AccountServiceInterface;

/**
 *
 * @author nguye
 */
@Controller
@RequestMapping(value = "", method = RequestMethod.GET)
public class ReplyApiCtr {

    AccountServiceFactory accountServiceFactory = new AccountServiceFactory();
    AccountServiceInterface accountServiceInterface = accountServiceFactory.getAccountService(
            AccountServiceFactory.USER_ACCOUNT_SERVICE
    );

    //luu phan hoi
    @RequestMapping(value = "reply/save", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String replySave(HttpServletRequest request, HttpSession session) {
        //phan hoi
        ResponseRequest responseRequest = null;
        ArrayList<ResponseRequest> responseRequests = new ArrayList<>();
        //cac thong tin
        User user = accountServiceInterface.checkLogin(session, request);
        if (user != null) {
            try {
                //
                String r_content = request.getParameter("r_content");
                int t_id = Integer.parseInt(request.getParameter("t_id"));
                Reply r = new Reply();
                r.setContent(r_content);
                r.setAuthor(user);
                //luu vao csdl
                ReplyConnection.getReplyConnection().addReply(r, t_id);

                responseRequest = new ResponseRequest(ResponseRequest.RESPONSE_ACCESS, "Lưu thành công!");
                responseRequests.add(responseRequest);
                responseRequest = new ResponseRequest(
                        "list_user",
                        ThreadFollowConnection.getAnswerFollowConnection().getListFollow(t_id)
                );
                responseRequests.add(responseRequest);
            } catch (Exception e) {
                responseRequest = new ResponseRequest(ResponseRequest.RESPONSE_ERROR, "Đã xảy ra lỗi, lưu câu hỏi không thành công!");
                responseRequests.add(responseRequest);
            }
        } else {
            responseRequest = new ResponseRequest(ResponseRequest.RESPONSE_ERROR, "Đã xảy ra lỗi, lưu câu hỏi không thành công!");
            responseRequests.add(responseRequest);
        }
        String result = new Gson().toJson(responseRequests);
        return result;
    }

    //luu bai viet khi chinh sua
    @RequestMapping(value = "reply/edit/save", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String replyEditSave(ModelMap model, HttpServletRequest request, HttpSession session) {
        ResponseRequest responseRequest = null;
        ArrayList<ResponseRequest> responseRequests = new ArrayList<>();

        try {
            //lay du lieu tu form
            String r_content = request.getParameter("r_content");
            int r_id = Integer.parseInt(request.getParameter("r_id"));
            //dong goi doi tuong
            Reply r = new Reply();
            r.setId(r_id);
            r.setContent(r_content);
            //luu voa csdl
            ReplyConnection.getReplyConnection().updateReply(r);
            responseRequest = new ResponseRequest(ResponseRequest.RESPONSE_ACCESS, "Lưu thành công!");
            responseRequests.add(responseRequest);
        } catch (Exception e) {
            responseRequest = new ResponseRequest(ResponseRequest.RESPONSE_ERROR, "Đã xảy ra lỗi, lưu không thành công!");
            responseRequests.add(responseRequest);
        }
        String result = new Gson().toJson(responseRequests);
        return result;
    }

    //xoa cau tra loi
    @RequestMapping(value = "/reply/delete", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String replyDelete(ModelMap model, HttpServletRequest request, HttpSession session) {
        ResponseRequest responseRequest = null;
        int r_id = Integer.parseInt(request.getParameter("r_id"));

        User user = accountServiceInterface.checkLogin(session, request);
        if (user != null) {
            ReplyConnection.getReplyConnection().deleteById(r_id);
            responseRequest = new ResponseRequest(ResponseRequest.RESPONSE_ACCESS, "ok");
        } else {
            responseRequest = new ResponseRequest(ResponseRequest.RESPONSE_ERROR, "not ok");
        }

        String result = new Gson().toJson(responseRequest);
        return result;
    }
}
