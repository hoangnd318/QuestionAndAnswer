/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.question_follow;

import java.util.ArrayList;

/**
 *
 * @author nguye
 */
public class QuestionFollowQuery implements QuestionFollowInterface{
    private QuestionFollowFacade qff;
    public QuestionFollowQuery(QuestionFollowFacade qff){
        this.qff = qff;
    }
    @Override
    public boolean addNewFollow(int question_id, int user_id, String role) {
        return this.qff.update(QuestionFollowFacade.ADD_NEW_FOLLOW, question_id, user_id, role);
    }

    @Override
    public boolean checkFollowed(int question_id, int user_id) {
        return this.qff.checkFollowed(question_id, user_id);
    }

    @Override
    public boolean deleteByQuestionId(int q_id) {
        ArrayList<Object> key = new ArrayList<>();
        key.add(q_id);
        return this.qff.delete(QuestionFollowFacade.DELETE_BY_QUESTION_ID, key);
    }

    @Override
    public String getListFollow(int q_id) {
        return this.qff.get(q_id);
    }
    
}
