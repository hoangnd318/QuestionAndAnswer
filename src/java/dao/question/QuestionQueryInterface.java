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
public interface QuestionQueryInterface {

    public ArrayList<Question> findAllTFIDF();
    
    public ArrayList<Question> findByTime();

    public ArrayList<Question> findByKey(String key, int page, int user_id);
    
    public ArrayList<Question> findByKeySortOld(String key, int page, int user_id);
    
    public ArrayList<Question> findByKeySortVote(String key, int page, int user_id);

    public ArrayList<Question> findById(int id);

    public ArrayList<Question> findAllNoAnswer(int page, int user_id);
    
    public ArrayList<Question> findAllNoAnswerSortOld(int page, int user_id);
    
    public ArrayList<Question> findAllNoAnswerSortVote(int page, int user_id);

    public ArrayList<Question> findByIdAndTiltRaw(int question_id, String title_raw, int user_id);

    public ArrayList<Question> findByCommentId(int comment_id);

    public ArrayList<Question> findByUserIdSortNew(int user_id, int user_seen_id, int page);

    public ArrayList<Question> findByUserIdSortOld(int user_id, int user_seen_id, int page);
    
    public ArrayList<Question> findByTagName(String tag_name, int user_id, int page);
    
    public ArrayList<Question> findByTagNameSortOld(String tag_name, int user_id, int page);
    
    public ArrayList<Question> findByTagNameSortVote(String tag_name, int user_id, int page);
    
    public ArrayList<Question> findByTagQuestionOther(ArrayList<Tag> tags);

    public int countAllQuestion();
    
    public int countQuestionSearch(String key);
    
    public int countQuestionSearchByTag(String tag);

    public int countByUserId(int user_id);

    public int addNewQuestion(Question question);

    public int editQuestion(Question question);

    public int updateView(Question question);

    public boolean deleteByQuestionId(int q_id);
}
