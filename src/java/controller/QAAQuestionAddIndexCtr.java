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
public class QAAQuestionAddIndexCtr {

    AccountServiceFactory accountServiceFactory = new AccountServiceFactory();
    AccountServiceInterface accountServiceInterface = accountServiceFactory.getAccountService(
            AccountServiceFactory.USER_ACCOUNT_SERVICE
    );

    //them cau hoi
    @RequestMapping(value = "question/add")
    public String questionAdd(HttpServletRequest request, ModelMap model) {
        HttpSession session = request.getSession();
        User user = accountServiceInterface.checkLogin(session, request);
        if (user == null) {
            return "redirect:" + "/qanda";
        }
        return "view/frontend/templates/qanda/qanda_question_add";
    }

    //chinh sua cau hoi
    @RequestMapping(value = "/question/edit/{id}")
    public String questionEdit(
            HttpServletRequest request, HttpSession session,
            @PathVariable(value = "id") String question_id,
            ModelMap model
    ) {
        int int_question_id = Integer.parseInt(question_id);
        ArrayList<Question> q_tmp = QuestionConnection.getQuestionConnection().findById(int_question_id);
        Question question = null;
        if (q_tmp.size() != 0) {
            question = q_tmp.get(0);
        } else {
            return "view/frontend/templates/qanda/qanda_error";
        }
        User user = accountServiceInterface.checkLogin(session, request);
        if (user == null || user.getIdUser() != question.getAuthor().getIdUser()) {
            return "view/frontend/templates/qanda/qanda_error";
        }
        model.addAttribute(
                "question_detail",
                question
        );
        model.addAttribute(
                "tag_statistical",
                TagStatisticalConnection.getTagStatisticalConnection().getStatistical()
        );
        return "view/frontend/templates/qanda/qanda_question_add";
    }
}
