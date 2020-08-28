/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.question_follow;

/**
 *
 * @author nguye
 */
public interface QuestionFollowInterface {
    public boolean addNewFollow(int question_id, int user_id, String role);
    public boolean checkFollowed(int question_id, int user_id);
    public boolean deleteByQuestionId(int q_id);
    public String getListFollow(int q_id);
}
