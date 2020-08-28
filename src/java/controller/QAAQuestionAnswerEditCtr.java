/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.answer.AnswerConnection;
import dao.tag_statistical.TagStatisticalConnection;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import model.Answer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import service.AccountServiceFactory;
import service.AccountServiceInterface;
import service.CheckPermission;

/**
 *
 * @author nguye
 */
@Controller
@RequestMapping(value = "qanda", method = RequestMethod.GET)
public class QAAQuestionAnswerEditCtr {

    private CheckPermission checkPermission = new CheckPermission();

    //chinh sua cau tra loi
    @RequestMapping(value = "/question/answer/edit/{id}")
    public String questionAnswerEdit(
            HttpServletRequest request, HttpSession session,
            @PathVariable(value = "id") String answer_id,
            ModelMap model
    ) {
        int a_id = Integer.parseInt(answer_id);
        ArrayList<Answer> as = AnswerConnection.getAnswerConnection().findByAnswerId(a_id);
        if (!as.isEmpty()) {
            if (checkPermission.checkPermission(as.get(0).getAuthor(), session, request)) {
                model.addAttribute("answer", as.get(0));
            } else {
                return "view/frontend/templates/qanda/qanda_error";
            }
        } else {
            return "view/frontend/templates/qanda/qanda_error";
        }
        return "view/frontend/templates/qanda/qanda_answer_edit";
    }
}
