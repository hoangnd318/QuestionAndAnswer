/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.answer_follow;

import java.util.ArrayList;

/**
 *
 * @author nguye
 */
public class AnswerFollowQuery implements AnswerFollowInterface{
    private AnswerFollowFacade aff;
    
    public AnswerFollowQuery(AnswerFollowFacade aff){
        this.aff = aff;
    }
    
    @Override
    public boolean addNewFollow(int answer_id, int user_id, String role) {
        return this.aff.update(AnswerFollowFacade.ADD_NEW_FOLLOW, answer_id, user_id, role);
    }

    @Override
    public boolean checkFollowed(int answer_id, int user_id) {
        return this.aff.checkFollowed(answer_id, user_id);
    }

    @Override
    public boolean deleteByAnswerId(int a_id) {
        ArrayList<Object> key = new ArrayList<>();
        key.add(a_id);
        return this.aff.delete(AnswerFollowFacade.DELETE_BY_ANSWER_ID, key);
    }

    @Override
    public String getListFollow(int a_id) {
        return aff.get(a_id);
    }
    
}
