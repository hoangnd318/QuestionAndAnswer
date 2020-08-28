/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.question.QuestionConnection;
import dao.tag_statistical.TagStatisticalConnection;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import model.Question;
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
@RequestMapping(value = "qanda", method = RequestMethod.GET)
public class QAAIndexCtr {

    AccountServiceFactory accountServiceFactory = new AccountServiceFactory();
    AccountServiceInterface accountServiceInterface = accountServiceFactory.getAccountService(
            AccountServiceFactory.USER_ACCOUNT_SERVICE
    );

    //trang chu
    @RequestMapping
    public String index(HttpServletRequest request, ModelMap model, HttpSession session) {
        session = request.getSession();
        User user = accountServiceInterface.checkLogin(session, request);
        int u_id = -1;
        if (user != null) {
            u_id = user.getIdUser();
        }
        String typeSort = "moi-nhat";
        if (request.getParameter("sort") != null) {
            typeSort = request.getParameter("sort");
        }
        int page = 1;
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }
        if (typeSort == null || typeSort.compareTo("moi-nhat") == 0) {
            setContent(
                    model,
                    QuestionConnection.getQuestionConnection().countAllQuestion(),
                    QuestionConnection.getQuestionConnection().findAllNoAnswer(page, u_id)
            );
        } else if (typeSort.compareTo("cu-nhat") == 0) {
            setContent(
                    model,
                    QuestionConnection.getQuestionConnection().countAllQuestion(),
                    QuestionConnection.getQuestionConnection().findAllNoAnswerSortOld(page, u_id)
            );
        } else if (typeSort.compareTo("noi-bat") == 0) {
            setContent(
                    model,
                    QuestionConnection.getQuestionConnection().countAllQuestion(),
                    QuestionConnection.getQuestionConnection().findAllNoAnswerSortVote(page, u_id)
            );
        }
        model.addAttribute(
                "tag_statistical",
                TagStatisticalConnection.getTagStatisticalConnection().getStatistical()
        );
        model.addAttribute("page_current", page);
        model.addAttribute("type_sort", typeSort);
        return "view/frontend/templates/qanda/qanda_index";
    }

    //set content cho trang chu
    public void setContent(ModelMap model, int questionCounts, ArrayList<Question> questions) {
        int questionCount = questionCounts;
        model.addAttribute("questions", questions);
        model.addAttribute("questions_count", questionCount);
        int pageTotal = 0;
        if (questionCount % 10 == 0) {
            pageTotal = questionCount / 10;
        } else {
            pageTotal = (int) (questionCount / 10);
            pageTotal++;
        }
        model.addAttribute("page_total", pageTotal);
    }
}
