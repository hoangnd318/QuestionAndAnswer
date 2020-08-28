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
public interface AnswerQueryInterface {

    public int getCountAnswerOfQuestionById(int id);

    public ArrayList<Answer> findByQuestionId(int id, int user_id, int page);

    public ArrayList<Answer> findByQuestionIdSortOld(int id, int user_id, int page);

    public ArrayList<Answer> findByQuestionIdSortVote(int id, int user_id, int page);

    public ArrayList<Answer> findByUserIdSortNew(int user_id, int user_seen_id, int page);

    public ArrayList<Answer> findByUserIdSortOld(int user_id, int user_seen_id, int page);

    public ArrayList<Answer> findByAnswerId(int a_id);

    public int addNewAnswer(Answer answer, int question_id);

    public int editAnswer(Answer answer);

    public int voteBestAnswer(Answer answer);

    public int countByUserId(int user_id);

    public int countBestAnswerByUserId(int user_id);

    public boolean deleteByQuestionId(int q_id);

    public boolean deleteByAnswerId(int a_id);
}
