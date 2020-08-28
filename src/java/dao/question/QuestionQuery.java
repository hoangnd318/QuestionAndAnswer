/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.question;

import java.util.ArrayList;
import model.Question;
import model.Tag;

/**
 *
 * @author nguye
 */
public class QuestionQuery implements QuestionQueryInterface {

    QuestionQueryFacade questionQueryFacade;

    public QuestionQuery(QuestionQueryFacade questionQueryFacade) {
        this.questionQueryFacade = questionQueryFacade;
    }

    @Override
    public ArrayList<Question> findByTime() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Question> findByKey(String key, int page, int user_id) {
        ArrayList<Object> keyDT = new ArrayList<>();
        keyDT.add(key);
        keyDT.add(page);
        return this.questionQueryFacade.find(QuestionQueryFacade.FIND_BY_KEY, keyDT, user_id);
    }

    @Override
    public ArrayList<Question> findById(int id) {
        ArrayList<Object> key = new ArrayList<>();
        key.add(id);
        return this.questionQueryFacade.find(QuestionQueryFacade.FIND_BY_ID, key, -1);
    }

    @Override
    public ArrayList<Question> findAllNoAnswer(int page, int user_id) {
        ArrayList<Object> key = new ArrayList<>();
        key.add(page);
        return this.questionQueryFacade.find(QuestionQueryFacade.FIND_ALL_NO_ANSWER, key, user_id);
    }

    @Override
    public int countAllQuestion() {
        return this.questionQueryFacade.countQuestion(QuestionQueryFacade.COUNT_ALL_QUESTION, null);
    }

    @Override
    public int addNewQuestion(Question question) {
        return this.questionQueryFacade.update(QuestionQueryFacade.ADD_NEW_QUESTION, question);
    }

    @Override
    public int editQuestion(Question question) {
        return this.questionQueryFacade.update(QuestionQueryFacade.EDIT_QUESTION, question);
    }

    @Override
    public ArrayList<Question> findByIdAndTiltRaw(int question_id, String title_raw, int user_id) {
        ArrayList<Object> key = new ArrayList<>();
        key.add(question_id);
        key.add(title_raw);
        return this.questionQueryFacade.find(QuestionQueryFacade.FIND_BY_ID_AND_RAW_TITLE, key, user_id);
    }

    @Override
    public int updateView(Question question) {
        return this.questionQueryFacade.update(QuestionQueryFacade.UPDATE_VIEW, question);
    }

    @Override
    public ArrayList<Question> findByCommentId(int comment_id) {
        ArrayList<Object> key = new ArrayList<>();
        key.add(comment_id);
        return this.questionQueryFacade.find(QuestionQueryFacade.FIND_BY_COMMENT_ID, key, -1);
    }

    @Override
    public int countByUserId(int user_id) {
        ArrayList<Object> key = new ArrayList<>();
        key.add(user_id);
        return this.questionQueryFacade.countQuestion(QuestionQueryFacade.COUNT_BY_USER_ID, key);
    }

    @Override
    public ArrayList<Question> findByUserIdSortNew(int user_id, int user_seen_id, int page) {
        ArrayList<Object> key = new ArrayList<>();
        key.add(user_id);
        key.add(page);
        return this.questionQueryFacade.find(QuestionQueryFacade.FIND_BY_USER_ID_SORT_NEW, key, user_seen_id);
    }

    @Override
    public boolean deleteByQuestionId(int q_id) {
        ArrayList<Object> key = new ArrayList<>();
        key.add(q_id);
        return this.questionQueryFacade.delete(QuestionQueryFacade.DELETE_BY_QUESTION_ID, key);
    }

    @Override
    public ArrayList<Question> findByTagName(String tag_name, int user_id, int page) {
        ArrayList<Object> key = new ArrayList<>();
        key.add(tag_name);
        key.add(page);
        return this.questionQueryFacade.find(QuestionQueryFacade.FIND_BY_TAG_NAME, key, user_id);
    }

    @Override
    public int countQuestionSearch(String key) {
        ArrayList<Object> keyDT = new ArrayList<>();
        keyDT.add(key);
        return this.questionQueryFacade.countQuestion(QuestionQueryFacade.COUNT_SEARCH_QUESTION, keyDT);
    }

    @Override
    public int countQuestionSearchByTag(String tag) {
        ArrayList<Object> keyDT = new ArrayList<>();
        keyDT.add(tag);
        return this.questionQueryFacade.countQuestion(QuestionQueryFacade.COUNT_SEARCH_QUESTION_BY_TAG, keyDT);
    }

    @Override
    public ArrayList<Question> findByUserIdSortOld(int user_id, int user_seen_id, int page) {
        ArrayList<Object> key = new ArrayList<>();
        key.add(user_id);
        key.add(page);
        return this.questionQueryFacade.find(QuestionQueryFacade.FIND_BY_USER_ID_SORT_OLD, key, user_seen_id);
    }

    @Override
    public ArrayList<Question> findAllNoAnswerSortOld(int page, int user_id) {
        ArrayList<Object> key = new ArrayList<>();
        key.add(page);
        return this.questionQueryFacade.find(QuestionQueryFacade.FIND_ALL_NO_ANSWER_SORT_OLD, key, user_id);
    }

    @Override
    public ArrayList<Question> findAllNoAnswerSortVote(int page, int user_id) {
        ArrayList<Object> key = new ArrayList<>();
        key.add(page);
        return this.questionQueryFacade.find(QuestionQueryFacade.FIND_ALL_NO_ANSWER_SORT_VOTE, key, user_id);
    }

    @Override
    public ArrayList<Question> findByTagNameSortOld(String tag_name, int user_id, int page) {
        ArrayList<Object> key = new ArrayList<>();
        key.add(tag_name);
        key.add(page);
        return this.questionQueryFacade.find(QuestionQueryFacade.FIND_BY_TAG_NAME_SORT_OLD, key, user_id);
    }

    @Override
    public ArrayList<Question> findByTagNameSortVote(String tag_name, int user_id, int page) {
        ArrayList<Object> key = new ArrayList<>();
        key.add(tag_name);
        key.add(page);
        return this.questionQueryFacade.find(QuestionQueryFacade.FIND_BY_TAG_NAME_SORT_VOTE, key, user_id);
    }

    @Override
    public ArrayList<Question> findByKeySortOld(String key, int page, int user_id) {
        ArrayList<Object> keyDT = new ArrayList<>();
        keyDT.add(key);
        keyDT.add(page);
        return this.questionQueryFacade.find(QuestionQueryFacade.FIND_BY_KEY_SORT_OLD, keyDT, user_id);
    }

    @Override
    public ArrayList<Question> findByKeySortVote(String key, int page, int user_id) {
        ArrayList<Object> keyDT = new ArrayList<>();
        keyDT.add(key);
        keyDT.add(page);
        return this.questionQueryFacade.find(QuestionQueryFacade.FIND_BY_KEY_SORT_VOTE, keyDT, user_id);
    }

    @Override
    public ArrayList<Question> findAllTFIDF() {
        return this.questionQueryFacade.find(QuestionQueryFacade.FIND_ALL_TF_IDF, null, 0);
    }

    @Override
    public ArrayList<Question> findByTagQuestionOther(ArrayList<Tag> tags) {
        ArrayList<Object> keyDT = new ArrayList<>();
        for(Tag dt : tags){
            keyDT.add(dt.getName());
        }
        return this.questionQueryFacade.find(QuestionQueryFacade.FIND_RECOMMENDED, keyDT, 0);
    }
}
