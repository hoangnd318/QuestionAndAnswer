/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.google.gson.Gson;
import dao.answer_follow.AnswerFollowConnection;
import dao.comment.CommentConnection;
import dao.user.UserConnection;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import model.Comment;
import model.ResponseRequest;
import model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import service.AccountServiceFactory;
import service.AccountServiceInterface;
import service.CheckPermission;

/**
 *
 * @author nguye
 */
@Controller
@RequestMapping(value = "qanda", method = RequestMethod.GET)
public class QAACommentApiCtr {

    CheckPermission checkPermission = new CheckPermission();
    AccountServiceFactory accountServiceFactory = new AccountServiceFactory();
    AccountServiceInterface accountServiceInterface = accountServiceFactory.getAccountService(
            AccountServiceFactory.USER_ACCOUNT_SERVICE
    );

    //xoa binh luan
    @RequestMapping(value = "/question/answer/comment/delete", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String questionAnswerCommentDelete(ModelMap model, HttpServletRequest request, HttpSession session) {
        ResponseRequest responseRequest = null;
        int c_id = Integer.parseInt(request.getParameter("c_id"));
        User user = accountServiceInterface.checkLogin(session, request);
        if(user != null){
            CommentConnection.getCommentConnection().deleteById(c_id);
            responseRequest = new ResponseRequest(ResponseRequest.RESPONSE_ACCESS, "ok");
        }else{
            responseRequest = new ResponseRequest(ResponseRequest.RESPONSE_ERROR, "not ok");
        }
        String result = new Gson().toJson(responseRequest);
        return result;
    }
    
    //luu binh luan
    @RequestMapping(value = "question/answer/comment/edit/save", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String questionCommentEdit(
            HttpServletRequest request,
            HttpSession session
    ) {
        ResponseRequest responseRequest = null;
        int c_id = Integer.parseInt(request.getParameter("c_id"));
        String c_content = request.getParameter("c_content");
        if (checkPermission.checkPermission(null, session, request)) {
            Comment c = new Comment();
            c.setId(c_id);
            c.setContent(c_content);
            CommentConnection.getCommentConnection().editComment(c);
            responseRequest = new ResponseRequest(ResponseRequest.RESPONSE_ACCESS, "Sửa bình luận thành công");
        } else {
            responseRequest = new ResponseRequest(ResponseRequest.RESPONSE_ERROR, "not ok");
        }
        String result = new Gson().toJson(responseRequest);
        return result;
    }
    
    //them binh luan
    @RequestMapping(value = "/question/answer/comment/new", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String questionAnswerCommentNew(ModelMap model, HttpServletRequest request, HttpSession session) {
        ResponseRequest responseRequest = null;
        ArrayList<ResponseRequest> responseRequests = new ArrayList<>();
        try {
            int a_id = Integer.parseInt(request.getParameter("a_id"));
            int u_id = Integer.parseInt(request.getParameter("u_id"));
            String content = request.getParameter("c_content");

            Comment comment = new Comment();
            comment.setContent(content);
            comment.setAuthor(UserConnection.getUserConnection().findById(u_id));

            int c_id = CommentConnection.getCommentConnection().addNewComment(comment, a_id);

            responseRequest = new ResponseRequest(ResponseRequest.RESPONSE_ACCESS, "Thêm bình luận thành công!");
            responseRequests.add(responseRequest);
            responseRequest = new ResponseRequest(
                    "author_answer_id",
                    AnswerFollowConnection.getAnswerFollowConnection().getListFollow(a_id)
            );
            responseRequests.add(responseRequest);
        } catch (NumberFormatException e) {
            responseRequest = new ResponseRequest(ResponseRequest.RESPONSE_ERROR, "Đã xảy ra lỗi, lưu bình luận không thành công!");
        }
        String result = new Gson().toJson(responseRequests);
        return result;
    }
}
