/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.answer.AnswerConnection;
import dao.notification.NotificationConnection;
import dao.question.QuestionConnection;
import dao.tag_statistical.TagStatisticalConnection;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import model.Notification;
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
public class QAAQuestionIndexCtr {

    AccountServiceFactory accountServiceFactory = new AccountServiceFactory();
    AccountServiceInterface accountServiceInterface = accountServiceFactory.getAccountService(
            AccountServiceFactory.USER_ACCOUNT_SERVICE
    );

    //hien thi chi tiet cau hoi
    @RequestMapping(value = "/question/{question_id}/{title_raw:.+}")
    public String questionDetail(
            HttpServletRequest request, HttpSession session,
            @PathVariable(value = "question_id") String question_id,
            @PathVariable(value = "title_raw") String title_raw,
            ModelMap model
    ) {
        int page = 1;
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }
        String typeSort = "noi-bat";
        if (request.getParameter("sort") != null) {
            typeSort = request.getParameter("sort");
        }
        User user = accountServiceInterface.checkLogin(session, request);
        int u_id = -1;
        if (user != null) {
            u_id = user.getIdUser();
        }
        int q_id = Integer.parseInt(question_id);
        String checkNoti = request.getParameter("refe_noti");
        if (checkNoti != null) {
            int notify_id = Integer.parseInt(checkNoti);
            Notification n = new Notification();
            n.setId(notify_id);
            NotificationConnection.getNotificationConnection().changeStatus(n);
        }
        ArrayList<Question> questions = QuestionConnection.getQuestionConnection().findByIdAndTiltRaw(q_id, title_raw, u_id);
        if (!questions.isEmpty()) {
            model.addAttribute("question_detail", questions.get(0));
            int view = questions.get(0).getViews() + 1;
            questions.get(0).setViews(view);
            //tang view
            QuestionConnection.getQuestionConnection().updateView(questions.get(0));
            if (typeSort == null || typeSort.compareTo("noi-bat") == 0) {
                questions.get(0).setAnswers(AnswerConnection.getAnswerConnection().findByQuestionIdSortVote(q_id, u_id, page));
                setContent(
                        model,
                        AnswerConnection.getAnswerConnection().getCountAnswerOfQuestionById(questions.get(0).getId()),
                        questions.get(0)
                );
            } else if (typeSort.compareTo("cu-nhat") == 0) {
                questions.get(0).setAnswers(AnswerConnection.getAnswerConnection().findByQuestionIdSortOld(q_id, u_id, page));
                setContent(
                        model,
                        AnswerConnection.getAnswerConnection().getCountAnswerOfQuestionById(questions.get(0).getId()),
                        questions.get(0)
                );
            } else if (typeSort.compareTo("moi-nhat") == 0) {
                questions.get(0).setAnswers(AnswerConnection.getAnswerConnection().findByQuestionId(q_id, u_id, page));
                setContent(
                        model,
                        AnswerConnection.getAnswerConnection().getCountAnswerOfQuestionById(questions.get(0).getId()),
                        questions.get(0)
                );
            }
        } else {
            return "view/frontend/templates/qanda/qanda_error";
        }
        String url = request.getContextPath() + "/qanda/question/" + question_id + "/" + title_raw + "?sort=" + typeSort;
        model.addAttribute("page_url", url);
        model.addAttribute("page_current", page);
        model.addAttribute("type_sort", typeSort);
        model.addAttribute(
                "tag_statistical",
                TagStatisticalConnection.getTagStatisticalConnection().getStatistical()
        );
        return "view/frontend/templates/qanda/qanda_question_detail";
    }

    public void setContent(ModelMap model, int count, Question question) {
        model.addAttribute("question_detail", question);
        model.addAttribute("count", count);
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
