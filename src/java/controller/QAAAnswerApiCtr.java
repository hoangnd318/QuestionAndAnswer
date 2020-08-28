/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.google.gson.Gson;
import dao.answer.AnswerConnection;
import dao.answer_vote.AnswerVoteConnection;
import dao.question_follow.QuestionFollowConnection;
import dao.user.UserConnection;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import model.Answer;
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
public class QAAAnswerApiCtr {

    CheckPermission checkPermission = new CheckPermission();
    AccountServiceFactory accountServiceFactory = new AccountServiceFactory();
    AccountServiceInterface accountServiceInterface = accountServiceFactory.getAccountService(
            AccountServiceFactory.USER_ACCOUNT_SERVICE
    );

    //xoa cau tra loi
    @RequestMapping(value = "/question/answer/delete", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String questionAnswerDelete(ModelMap model, HttpServletRequest request, HttpSession session) {
        ResponseRequest responseRequest = null;
        int a_id = Integer.parseInt(request.getParameter("a_id"));

        User user = accountServiceInterface.checkLogin(session, request);
        if (user != null) {
            AnswerConnection.getAnswerConnection().deleteByAnswerId(a_id);
            responseRequest = new ResponseRequest(ResponseRequest.RESPONSE_ACCESS, "ok");
        } else {
            responseRequest = new ResponseRequest(ResponseRequest.RESPONSE_ERROR, "not ok");
        }

        String result = new Gson().toJson(responseRequest);
        return result;
    }

    //chinh sua cau tra loi
    @RequestMapping(value = "question/answer/edit/save", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String questionAnswerEdit(
            HttpServletRequest request,
            HttpSession session
    ) {
        ResponseRequest responseRequest = null;
        int a_id = Integer.parseInt(request.getParameter("a_id"));
        String a_content = request.getParameter("a_content");
        String a_content_raw = request.getParameter("a_content_raw");
        Answer a = new Answer();
        a.setId(a_id);
        a.setContent(a_content);
        a.setContent_raw(a_content_raw);
        if (checkPermission.checkPermission(null, session, request)) {
            AnswerConnection.getAnswerConnection().editAnswer(a);
            responseRequest = new ResponseRequest(ResponseRequest.RESPONSE_ACCESS, "Sửa câu trả lời thành công");
        } else {
            responseRequest = new ResponseRequest(ResponseRequest.RESPONSE_ERROR, "not ok");
        }
        String result = new Gson().toJson(responseRequest);
        return result;
    }

    //them cau tra loi
    @RequestMapping(value = "/question/answer/new", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String questionAnswerNew(ModelMap model, HttpServletRequest request, HttpSession session) {
        ResponseRequest responseRequest = null;
        ArrayList<ResponseRequest> responseRequests = new ArrayList<>();
        try {
            String content = request.getParameter("a_content");
            String content_raw = request.getParameter("a_content_raw");
            int q_id = Integer.parseInt(request.getParameter("q_id"));
            int u_id = Integer.parseInt(request.getParameter("u_id"));
            Answer answer = new Answer();
            answer.setBestAnswer(0);
            answer.setContent(content);
            answer.setContent_raw(content_raw);
            answer.setAuthor(UserConnection.getUserConnection().findById(u_id));
            int a_id = AnswerConnection.getAnswerConnection().addNewAnswer(answer, q_id);

            responseRequest = new ResponseRequest(ResponseRequest.RESPONSE_ACCESS, "Thêm câu trả lời thành công!");
            responseRequests.add(responseRequest);
            responseRequest = new ResponseRequest(
                    "author_question_id",
                    QuestionFollowConnection.getQuestionFollowConnection().getListFollow(q_id)
            );
            responseRequests.add(responseRequest);
        } catch (NumberFormatException e) {
            responseRequest = new ResponseRequest(ResponseRequest.RESPONSE_ERROR, "Đã xảy ra lỗi, lưu câu trả lời không thành công!");
            responseRequests.add(responseRequest);
        }
        String result = new Gson().toJson(responseRequests);
        return result;
    }

    // vote best answer
    @RequestMapping(value = "question/answer/bestanswer/vote", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String voteBestAnswer(ModelMap model, HttpServletRequest request, HttpSession session) {
        ResponseRequest responseRequest = null;
        int a_id = Integer.parseInt(request.getParameter("a_id"));

        User user = accountServiceInterface.checkLogin(session, request);
        if (user != null) {
            Answer answer = new Answer();
            answer.setId(a_id);
            AnswerConnection.getAnswerConnection().voteBestAnswer(answer);
            responseRequest = new ResponseRequest(ResponseRequest.RESPONSE_ACCESS, "ok");
        } else {
            responseRequest = new ResponseRequest(ResponseRequest.RESPONSE_ERROR, "not ok");
        }
        String result = new Gson().toJson(responseRequest);
        return result;
    }

    //vote cau tra loi
    @RequestMapping(value = "/question/answer/vote", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String questionAnswerVote(ModelMap model, HttpServletRequest request, HttpSession session) {
        ResponseRequest responseRequest = null;
        try {
            int a_id = Integer.parseInt(request.getParameter("a_id"));
            int u_id = Integer.parseInt(request.getParameter("u_id"));
            String type = request.getParameter("type");
            if (type.compareTo("un_vote") == 0) {
                AnswerVoteConnection.getAnswerVoteConnection().unVoteAnswer(a_id, u_id);
            } else if (type.compareTo("change_vote_up") == 0) {
                AnswerVoteConnection.getAnswerVoteConnection().changeVoteAnswer("vote_up", a_id, u_id);
            } else if (type.compareTo("change_vote_down") == 0) {
                AnswerVoteConnection.getAnswerVoteConnection().changeVoteAnswer("vote_down", a_id, u_id);
            } else {
                AnswerVoteConnection.getAnswerVoteConnection().voteAnswer(type, a_id, u_id);
            }
            responseRequest = new ResponseRequest(ResponseRequest.RESPONSE_ACCESS, "Thành công!");
        } catch (NumberFormatException e) {
            responseRequest = new ResponseRequest(ResponseRequest.RESPONSE_ERROR, "Đã xảy ra lỗi, lưu bình luận không thành công!");
        }
        String result = new Gson().toJson(responseRequest);
        return result;
    }
}
