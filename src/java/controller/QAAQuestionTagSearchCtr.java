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
@RequestMapping(value = "qanda", method = RequestMethod.GET)
public class QAAQuestionTagSearchCtr {

    AccountServiceFactory accountServiceFactory = new AccountServiceFactory();
    AccountServiceInterface accountServiceInterface = accountServiceFactory.getAccountService(
            AccountServiceFactory.USER_ACCOUNT_SERVICE
    );

    @RequestMapping(value = "question/search/tag/{name:.+}")
    public String questionSearchTag(
            HttpServletRequest request, HttpSession session,
            @PathVariable(value = "name") String tag_name,
            ModelMap model
    ) {
        User user = accountServiceInterface.checkLogin(session, request);
        int page = 1;
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }
        int u_id = -1;
        if (user != null) {
            u_id = user.getIdUser();
        }
        String typeSort = "noi-bat";
        if (request.getParameter("sort") != null) {
            typeSort = request.getParameter("sort");
        }
        if (typeSort == null || typeSort.compareTo("moi-nhat") == 0) {
            setContent(
                    model,
                    QuestionConnection.getQuestionConnection().countQuestionSearchByTag(tag_name),
                    QuestionConnection.getQuestionConnection().findByTagName(tag_name, u_id, page)
            );
        } else if (typeSort.compareTo("cu-nhat") == 0) {
            setContent(
                    model,
                    QuestionConnection.getQuestionConnection().countQuestionSearchByTag(tag_name),
                    QuestionConnection.getQuestionConnection().findByTagNameSortOld(tag_name, u_id, page)
            );
        } else if (typeSort.compareTo("noi-bat") == 0) {
            setContent(
                    model,
                    QuestionConnection.getQuestionConnection().countQuestionSearchByTag(tag_name),
                    QuestionConnection.getQuestionConnection().findByTagNameSortVote(tag_name, u_id, page)
            );
        }
        model.addAttribute("page_current", page);
        model.addAttribute(
                "tag_statistical",
                TagStatisticalConnection.getTagStatisticalConnection().getStatistical()
        );
        //
        String url = request.getContextPath() + "/qanda/question/search/tag/" + tag_name + "?";
        model.addAttribute("page_url", url);
        model.addAttribute("type_sort", typeSort);
        //
        return "view/frontend/templates/qanda/qanda_search_result";
    }

    public void setContent(ModelMap model, int count, ArrayList<Question> questions) {
        model.addAttribute(
                "q_search",
                questions
        );
        model.addAttribute("q_count", count);
        int pageTotal = 0;
        if (count % 10 == 0) {
            pageTotal = count / 10;
        } else {
            pageTotal = (int) (count / 10);
            pageTotal++;
        }
        model.addAttribute("page_total", pageTotal);
    }
}
