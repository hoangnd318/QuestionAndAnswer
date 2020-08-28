/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.google.gson.Gson;
import dao.group_follow.GroupFollowConnection;
import dao.reply.ReplyConnection;
import dao.thread_follow.ThreadFollowConnection;
import dao.threads.ThreadConnection;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import model.ResponseRequest;
import model.Threads;
import model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import service.AccountServiceFactory;
import service.AccountServiceInterface;
import service.ConvertSEO;

/**
 *
 * @author nguye
 */
@Controller
@RequestMapping(value = "", method = RequestMethod.GET)
public class ThreadApiCtr {

    AccountServiceFactory accountServiceFactory = new AccountServiceFactory();
    AccountServiceInterface accountServiceInterface = accountServiceFactory.getAccountService(
            AccountServiceFactory.USER_ACCOUNT_SERVICE
    );

    //luu bai viet
    @RequestMapping(value = "threads/save", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String threadSave(HttpServletRequest request, HttpSession session) {
        //phan hoi
        ResponseRequest responseRequest = null;
        ArrayList<ResponseRequest> responseRequests = new ArrayList<>();
        //cac thong tin
        User user = accountServiceInterface.checkLogin(session, request);
        if (user != null) {
            try {
                String t_title = request.getParameter("t_title");
                String t_content = request.getParameter("t_content");
                int g_id = Integer.parseInt(request.getParameter("g_id"));
                //dong goi doi tuong
                Threads t = new Threads();
                t.setTitle(t_title);
                t.setContent(t_content);
                t.setAuthor(user);
                //luu vao csdl
                Integer thread_id = ThreadConnection.getThreadConnection().addThread(t, g_id);
                ConvertSEO convertSEO = new ConvertSEO();
                String title_raw = convertSEO.convertStringToSEO(t_title);
                responseRequest = new ResponseRequest(ResponseRequest.RESPONSE_ACCESS, "Lưu thành công!");
                responseRequests.add(responseRequest);
                responseRequest = new ResponseRequest("id", thread_id.toString());
                responseRequests.add(responseRequest);
                responseRequest = new ResponseRequest("title", title_raw);
                responseRequests.add(responseRequest);

                responseRequest = new ResponseRequest(
                        "list_user",
                        GroupFollowConnection.getAnswerConnection().getListFollow(g_id)
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
    @RequestMapping(value = "threads/edit/save", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String threadEditSave(ModelMap model, HttpServletRequest request, HttpSession session) {
        ResponseRequest responseRequest = null;
        ArrayList<ResponseRequest> responseRequests = new ArrayList<>();
        try {
            //lay du lieu tu form
            String t_title = request.getParameter("t_title");
            String t_content = request.getParameter("t_content");
            int t_id = Integer.parseInt(request.getParameter("t_id"));
            //dong goi doi tuong
            Threads t = new Threads();
            t.setId(t_id);
            t.setTitle(t_title);
            t.setContent(t_content);
            //luu vao csdl
            int tmp = ThreadConnection.getThreadConnection().updateThread(t);
            //
            ConvertSEO convertSEO = new ConvertSEO();
            String title_raw = convertSEO.convertStringToSEO(t_title);
            responseRequest = new ResponseRequest(ResponseRequest.RESPONSE_ACCESS, "Lưu thành công!");
            responseRequests.add(responseRequest);
            responseRequest = new ResponseRequest("title", title_raw);
            responseRequests.add(responseRequest);
        } catch (Exception e) {
            responseRequest = new ResponseRequest(ResponseRequest.RESPONSE_ERROR, "Đã xảy ra lỗi, lưu không thành công!");
            responseRequests.add(responseRequest);
        }
        String result = new Gson().toJson(responseRequests);
        return result;
    }

    //xoa thread
    @RequestMapping(value = "threads/delete", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String threadDelete(ModelMap model, HttpServletRequest request, HttpSession session) {
        //phan hoi
        ResponseRequest responseRequest = null;
        //cac thong tin
        User user = accountServiceInterface.checkLogin(session, request);
        if (user != null) {
            try {
                int t_id = Integer.parseInt(request.getParameter("t_id"));

                //xoa follow
                ThreadFollowConnection.getAnswerFollowConnection().deleteByThreadId(t_id);
                //xoa reply
                ReplyConnection.getReplyConnection().deleteByThreadId(t_id);
                //xoa thread
                ThreadConnection.getThreadConnection().deleteById(t_id);

                responseRequest = new ResponseRequest(ResponseRequest.RESPONSE_ACCESS, "Lưu thành công!");
            } catch (Exception e) {
                responseRequest = new ResponseRequest(ResponseRequest.RESPONSE_ERROR, "Đã xảy ra lỗi, lưu câu hỏi không thành công!");
            }
        } else {
            responseRequest = new ResponseRequest(ResponseRequest.RESPONSE_ERROR, "Đã xảy ra lỗi, lưu câu hỏi không thành công!");
        }
        String result = new Gson().toJson(responseRequest);
        return result;
    }
}
