/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.answer.AnswerConnection;
import dao.question.QuestionConnection;
import dao.tag_statistical.TagStatisticalConnection;
import dao.user.UserConnection;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import model.Answer;
import model.Question;
import model.User;
import model.UserStatistical;
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
public class QAAProfileIndexCtr {

    AccountServiceFactory accountServiceFactory = new AccountServiceFactory();
    AccountServiceInterface accountServiceInterface = accountServiceFactory.getAccountService(
            AccountServiceFactory.USER_ACCOUNT_SERVICE
    );

    //thong tin tai khoan
    @RequestMapping(value = "user/profile/{p_type}")
    public String userProfile(
            HttpServletRequest request,
            ModelMap model,
            HttpSession session,
            @PathVariable(value = "p_type") String p_type
    ) {
        //get user
        User user = accountServiceInterface.checkLogin(session, request);
        if (user == null) {
            return "view/frontend/templates/qanda/qanda_error";
        }
        // lay so trang
        int page = 1;
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }
        //
        //lay kieu sort
        String typeSort = "moi-nhat";
        if (request.getParameter("sort") != null) {
            typeSort = request.getParameter("sort");
        }
        //
        model.addAttribute("p_type", p_type);

        UserStatistical us = new UserStatistical();
        us.setCountQuestion(QuestionConnection.getQuestionConnection().countByUserId(user.getIdUser()));
        us.setCountAnswer(AnswerConnection.getAnswerConnection().countByUserId(user.getIdUser()));
        us.setCountBestAnswer(AnswerConnection.getAnswerConnection().countBestAnswerByUserId(user.getIdUser()));
        if (p_type.compareTo("question") == 0) {
            if (typeSort == null || typeSort.compareTo("moi-nhat") == 0) {
                setContentQuestion(
                        model,
                        QuestionConnection.getQuestionConnection().countByUserId(user.getIdUser()),
                        QuestionConnection.getQuestionConnection().findByUserIdSortNew(user.getIdUser(), user.getIdUser(), page)
                );
                String url = request.getContextPath() + "/qanda/user/profile/question?sort=moi-nhat&";
                model.addAttribute("page_url", url);
            } else if (typeSort.compareTo("cu-nhat") == 0) {
                setContentQuestion(
                        model,
                        QuestionConnection.getQuestionConnection().countByUserId(user.getIdUser()),
                        QuestionConnection.getQuestionConnection().findByUserIdSortOld(user.getIdUser(), user.getIdUser(), page)
                );
                String url = request.getContextPath() + "/qanda/user/profile/question?sort=cu-nhat&";
                model.addAttribute("page_url", url);
            }
        } else if (p_type.compareTo("answer") == 0) {
            if (typeSort == null || typeSort.compareTo("moi-nhat") == 0) {
                setContentAnswer(
                        model,
                        AnswerConnection.getAnswerConnection().countByUserId(user.getIdUser()),
                        AnswerConnection.getAnswerConnection().findByUserIdSortNew(user.getIdUser(), user.getIdUser(), page)
                );
                String url = request.getContextPath() + "/qanda/user/profile/answer?sort=moi-nhat&";
                model.addAttribute("page_url", url);
            } else if (typeSort.compareTo("cu-nhat") == 0) {
                setContentAnswer(
                        model,
                        AnswerConnection.getAnswerConnection().countByUserId(user.getIdUser()),
                        AnswerConnection.getAnswerConnection().findByUserIdSortOld(user.getIdUser(), user.getIdUser(), page)
                );
                String url = request.getContextPath() + "/qanda/user/profile/answer?sort=cu-nhat&";
                model.addAttribute("page_url", url);
            }
        }
        model.addAttribute("type_sort", typeSort);
        model.addAttribute(
                "tag_statistical",
                TagStatisticalConnection.getTagStatisticalConnection().getStatistical()
        );
        model.addAttribute("user_statistical", us);
        model.addAttribute("page_current", page);
        return "view/frontend/templates/qanda/qanda_user_profile";
    }

    //thong tin tai khoan
    @RequestMapping(value = "user/profile/{p_type}/{id_user}")
    public String userProfileGuest(
            HttpServletRequest request,
            ModelMap model,
            HttpSession session,
            @PathVariable(value = "p_type") String p_type,
            @PathVariable(value = "id_user") String id_user
    ) {
        // lay so trang
        int page = 1;
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }
        //
        //lay kieu sort
        String typeSort = "moi-nhat";
        if (request.getParameter("sort") != null) {
            typeSort = request.getParameter("sort");
        }

        //get user
        int user_id = Integer.parseInt(id_user);
        model.addAttribute("guest_id", user_id);
        User user = UserConnection.getUserConnection().findById(user_id);
        UserStatistical us = new UserStatistical();
        us.setCountQuestion(QuestionConnection.getQuestionConnection().countByUserId(user.getIdUser()));
        us.setCountAnswer(AnswerConnection.getAnswerConnection().countByUserId(user.getIdUser()));
        us.setCountBestAnswer(AnswerConnection.getAnswerConnection().countBestAnswerByUserId(user.getIdUser()));
        if (p_type.compareTo("question") == 0) {
            if (typeSort == null || typeSort.compareTo("moi-nhat") == 0) {
                setContentQuestion(
                        model,
                        QuestionConnection.getQuestionConnection().countByUserId(user.getIdUser()),
                        QuestionConnection.getQuestionConnection().findByUserIdSortNew(user.getIdUser(), user.getIdUser(), page)
                );
                String url = request.getContextPath() + "/qanda/user/profile/question/" + user.getIdUser() + "?sort=moi-nhat&";
                model.addAttribute("page_url", url);
            } else if (typeSort.compareTo("cu-nhat") == 0) {
                setContentQuestion(
                        model,
                        QuestionConnection.getQuestionConnection().countByUserId(user.getIdUser()),
                        QuestionConnection.getQuestionConnection().findByUserIdSortOld(user.getIdUser(), user.getIdUser(), page)
                );
                String url = request.getContextPath() + "/qanda/user/profile/question/" + user.getIdUser() + "?sort=cu-nhat&";
                model.addAttribute("page_url", url);
            }
        } else if (p_type.compareTo("answer") == 0) {
            if (typeSort == null || typeSort.compareTo("moi-nhat") == 0) {
                setContentAnswer(
                        model,
                        AnswerConnection.getAnswerConnection().countByUserId(user.getIdUser()),
                        AnswerConnection.getAnswerConnection().findByUserIdSortNew(user.getIdUser(), user.getIdUser(), page)
                );
                String url = request.getContextPath() + "/qanda/user/profile/answer/" + user.getIdUser() + "?sort=moi-nhat&";
                model.addAttribute("page_url", url);
            } else if (typeSort.compareTo("cu-nhat") == 0) {
                setContentAnswer(
                        model,
                        AnswerConnection.getAnswerConnection().countByUserId(user.getIdUser()),
                        AnswerConnection.getAnswerConnection().findByUserIdSortOld(user.getIdUser(), user.getIdUser(), page)
                );
                String url = request.getContextPath() + "/qanda/user/profile/answer/" + user.getIdUser() + "?sort=cu-nhat&";
                model.addAttribute("page_url", url);
            }
        }
        model.addAttribute("type_sort", typeSort);
        model.addAttribute(
                "tag_statistical",
                TagStatisticalConnection.getTagStatisticalConnection().getStatistical()
        );
        model.addAttribute("user_statistical", us);
        model.addAttribute("page_current", page);
        return "view/frontend/templates/qanda/qanda_user_profile";
    }

    public void setContentQuestion(ModelMap model, int count, ArrayList<Question> questions) {
        model.addAttribute(
                "user_questions",
                questions
        );
        //
        int pageTotal = 0;
        if (count % 10 == 0) {
            pageTotal = count / 10;
        } else {
            pageTotal = (int) (count / 10);
            pageTotal++;
        }
        model.addAttribute("page_total", pageTotal);
        model.addAttribute("count", count);
    }

    public void setContentAnswer(ModelMap model, int count, ArrayList<Answer> answers) {
        model.addAttribute(
                "user_answers",
                answers
        );
        //
        int pageTotal = 0;
        if (count % 10 == 0) {
            pageTotal = count / 10;
        } else {
            pageTotal = (int) (count / 10);
            pageTotal++;
        }
        model.addAttribute("page_total", pageTotal);
        model.addAttribute("count", count);
    }
}
