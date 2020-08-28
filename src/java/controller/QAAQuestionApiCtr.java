/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.google.gson.Gson;
import dao.question.QuestionConnection;
import dao.question_tag.QuestionTagConnection;
import dao.question_vote.QuestionVoteConnection;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import model.Question;
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
@RequestMapping(value = "qanda", method = RequestMethod.GET)
public class QAAQuestionApiCtr {

    AccountServiceFactory accountServiceFactory = new AccountServiceFactory();
    AccountServiceInterface accountServiceInterface = accountServiceFactory.getAccountService(
            AccountServiceFactory.USER_ACCOUNT_SERVICE
    );

    //xoa bai viet
    @RequestMapping(value = "question/delete", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String questionDelete(ModelMap model, HttpServletRequest request, HttpSession session) {
        ResponseRequest responseRequest = null;
        int q_id = Integer.parseInt(request.getParameter("q_id"));
        User user = accountServiceInterface.checkLogin(session, request);
        if (user != null) {
            QuestionConnection.getQuestionConnection().deleteByQuestionId(q_id);
            responseRequest = new ResponseRequest(ResponseRequest.RESPONSE_ACCESS, "ok");
        } else {
            responseRequest = new ResponseRequest(ResponseRequest.RESPONSE_ERROR, "not ok");
        }
        String result = new Gson().toJson(responseRequest);
        return result;
    }

    //luu cau hoi khi them moi
    @RequestMapping(value = "question/save", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String questionSave(HttpServletRequest request, HttpSession session) {
        ResponseRequest responseRequest = null;
        ArrayList<ResponseRequest> responseRequests = new ArrayList<>();
        try {
            session = request.getSession();
            //lay du lieu tu form
            String title = request.getParameter("q_title");
            String content = request.getParameter("q_content");
            String content_raw = request.getParameter("q_content_raw");
            String tags = request.getParameter("q_tags");

            //khoi tao doi tuong
            Question question = new Question();
            question.setTitle(title);
            question.setContent(content);
            question.setContent_raw(content_raw);
            question.setAuthor(accountServiceInterface.checkLogin(session, request));
            question.setViews(0);
            //them cau hoi
            Integer question_id = QuestionConnection.getQuestionConnection().addNewQuestion(question);

            //luu du lieu tag
            QuestionTagConnection.getQuestionTagConnection().addTagQuestion(question_id, tags);
            responseRequest = new ResponseRequest(ResponseRequest.RESPONSE_ACCESS, "Lưu câu hỏi thành công!");
            responseRequests.add(responseRequest);
            responseRequest = new ResponseRequest("id", question_id.toString());
            responseRequests.add(responseRequest);
        } catch (Exception e) {
            responseRequest = new ResponseRequest(ResponseRequest.RESPONSE_ERROR, "Đã xảy ra lỗi, lưu câu hỏi không thành công!");
            responseRequests.add(responseRequest);
        }
        String result = new Gson().toJson(responseRequests);
        return result;
    }

    //luu cau hoi khi chinh sua
    @RequestMapping(value = "/question/edit/save", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String questionEditSave(ModelMap model, HttpServletRequest request, HttpSession session) {
        ResponseRequest responseRequest = null;
        session = request.getSession();
        try {
            session = request.getSession();
            //lay du lieu tu form
            int question_id = Integer.parseInt(request.getParameter("q_id"));
            String title = request.getParameter("q_title");
            String content = request.getParameter("q_content");
            String content_raw = request.getParameter("q_content_raw");
            String tags = request.getParameter("q_tags");

            //khoi tao doi tuong
            Question question = new Question();
            question.setId(question_id);
            question.setTitle(title);
            question.setContent(content);
            question.setContent_raw(content_raw);

            //luu cau hoi
            QuestionConnection.getQuestionConnection().editQuestion(question);

            //xoa du lieu tag
            QuestionTagConnection.getQuestionTagConnection().removeTag(question_id);
            //luu du lieu tag
            QuestionTagConnection.getQuestionTagConnection().addTagQuestion(question_id, tags);

            //
            responseRequest = new ResponseRequest(ResponseRequest.RESPONSE_ACCESS, "Lưu câu hỏi thành công!");
        } catch (NumberFormatException e) {
            responseRequest = new ResponseRequest(ResponseRequest.RESPONSE_ERROR, "Đã xảy ra lỗi, lưu câu hỏi không thành công!");
        }
        String result = new Gson().toJson(responseRequest);
        return result;
    }

    //vote_up cau hoi trong trang chi tiet cau hoi
    @RequestMapping(value = "/question/vote", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String questionVote(ModelMap model, HttpServletRequest request, HttpSession session) {
        ResponseRequest responseRequest = null;
        try {
            int q_id = Integer.parseInt(request.getParameter("q_id"));
            int u_id = Integer.parseInt(request.getParameter("u_id"));
            String type = request.getParameter("type");
            if (type.compareTo("un_vote") == 0) {
                QuestionVoteConnection.getQuestionVoteConnection().unVoteQuestion(q_id, u_id);
            } else if (type.compareTo("change_vote_up") == 0) {
                QuestionVoteConnection.getQuestionVoteConnection().changeVoteQuestion("vote_up", q_id, u_id);
            } else if (type.compareTo("change_vote_down") == 0) {
                QuestionVoteConnection.getQuestionVoteConnection().changeVoteQuestion("vote_down", q_id, u_id);
            } else {
                QuestionVoteConnection.getQuestionVoteConnection().voteQuestion(type, q_id, u_id);
            }
            responseRequest = new ResponseRequest(ResponseRequest.RESPONSE_ACCESS, "Thành công!");
        } catch (NumberFormatException e) {
            responseRequest = new ResponseRequest(ResponseRequest.RESPONSE_ERROR, "Đã xảy ra lỗi, lưu bình luận không thành công!");
        }
        String result = new Gson().toJson(responseRequest);
        return result;
    }
}
