/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.question_vote;

/**
 *
 * @author nguye
 */
public interface QuestionVoteInterface {
    public int getVoteByQuestionId(int id);
    public String checkVoted(int question_id, int user_id);
    public boolean voteQuestion(String typeVote, int q_id, int u_id);
    public boolean unVoteQuestion(int q_id, int u_id);
    public boolean changeVoteQuestion(String typeVote, int q_id, int u_id);
    public boolean deleteByQuestionId(int q_id);
}
