/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.answer;

import java.util.ArrayList;
import model.Answer;

/**
 *
 * @author nguye
 */
public class AnswerQuery implements AnswerQueryInterface {

    private AnswerQueryFacade answerQueryFacade;

    public AnswerQuery(AnswerQueryFacade answerQueryFacade) {
        this.answerQueryFacade = answerQueryFacade;
    }

    @Override
    public int getCountAnswerOfQuestionById(int id) {
        ArrayList<Object> key = new ArrayList<>();
        key.add(id);
        return this.answerQueryFacade.getCountAnswer(AnswerQueryFacade.GET_COUNT_ANSWER_OF_QUESTION, key);
    }

    @Override
    public ArrayList<Answer> findByQuestionId(int id, int user_id, int page) {
        ArrayList<Object> key = new ArrayList<>();
        key.add(id);
        key.add(page);
        return this.answerQueryFacade.find(AnswerQueryFacade.GET_ANSWER_BY_QUESTION_ID, key, user_id);
    }

    @Override
    public int addNewAnswer(Answer answer, int question_id) {
        return this.answerQueryFacade.update(AnswerQueryFacade.ADD_NEW_ANSWER, answer, question_id);
    }

    @Override
    public int countByUserId(int user_id) {
        ArrayList<Object> key = new ArrayList<>();
        key.add(user_id);
        return this.answerQueryFacade.getCountAnswer(AnswerQueryFacade.COUNT_BY_USER_ID, key);
    }

    @Override
    public int countBestAnswerByUserId(int user_id) {
        ArrayList<Object> key = new ArrayList<>();
        key.add(user_id);
        return this.answerQueryFacade.getCountAnswer(AnswerQueryFacade.COUNT_BEST_ANSWER_BY_USER_ID, key);
    }

    @Override
    public ArrayList<Answer> findByUserIdSortNew(int user_id, int user_seen_id, int page) {
        ArrayList<Object> key = new ArrayList<>();
        key.add(user_id);
        key.add(page);
        return this.answerQueryFacade.find(AnswerQueryFacade.GET_ANSWER_BY_USER_ID_SORT_NEW, key, user_id);
    }

    @Override
    public boolean deleteByQuestionId(int q_id) {
        ArrayList<Object> key = new ArrayList<>();
        key.add(q_id);
        return this.answerQueryFacade.delete(AnswerQueryFacade.DELETE_BY_QUESTION_ID, key);
    }

    @Override
    public boolean deleteByAnswerId(int a_id) {
        ArrayList<Object> key = new ArrayList<>();
        key.add(a_id);
        return this.answerQueryFacade.delete(AnswerQueryFacade.DELETE_BY_ANSWER_ID, key);
    }

    @Override
    public ArrayList<Answer> findByAnswerId(int a_id) {
        ArrayList<Object> key = new ArrayList<>();
        key.add(a_id);
        return this.answerQueryFacade.find(AnswerQueryFacade.GET_BY_ANSWER_ID, key, 0);
    }

    @Override
    public int editAnswer(Answer answer) {
        return this.answerQueryFacade.update(AnswerQueryFacade.EDIT_ANSWER, answer, 0);
    }

    @Override
    public int voteBestAnswer(Answer answer) {
        return this.answerQueryFacade.update(AnswerQueryFacade.VOTE_BEST_ANSWER, answer, 0);
    }

    @Override
    public ArrayList<Answer> findByUserIdSortOld(int user_id, int user_seen_id, int page) {
        ArrayList<Object> key = new ArrayList<>();
        key.add(user_id);
        key.add(page);
        return this.answerQueryFacade.find(AnswerQueryFacade.GET_ANSWER_BY_USER_ID_SORT_OLD, key, user_id);
    }

    @Override
    public ArrayList<Answer> findByQuestionIdSortOld(int id, int user_id, int page) {
        ArrayList<Object> key = new ArrayList<>();
        key.add(id);
        key.add(page);
        return this.answerQueryFacade.find(AnswerQueryFacade.GET_ANSWER_BY_QUESTION_ID_SORT_OLD, key, user_id);
    }

    @Override
    public ArrayList<Answer> findByQuestionIdSortVote(int id, int user_id, int page) {
        ArrayList<Object> key = new ArrayList<>();
        key.add(id);
        key.add(page);
        return this.answerQueryFacade.find(AnswerQueryFacade.GET_ANSWER_BY_QUESTION_ID_SORT_VOTE, key, user_id);
    }

}
