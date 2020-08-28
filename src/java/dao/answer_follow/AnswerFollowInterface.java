/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.answer_follow;

/**
 *
 * @author nguye
 */
public interface AnswerFollowInterface {
    public boolean addNewFollow(int answer_id, int user_id, String role);
    public boolean checkFollowed(int answer_id, int user_id);
    public boolean deleteByAnswerId(int a_id);
    public String getListFollow(int a_id);
}
