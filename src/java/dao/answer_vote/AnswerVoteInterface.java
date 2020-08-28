/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.answer_vote;

/**
 *
 * @author nguye
 */
public interface AnswerVoteInterface {
    public int getVoteByAnswerId(int id);
    public String checkVoted(int answer_id, int user_id);
    public boolean voteAnswer(String typeVote, int a_id, int u_id);
    public boolean unVoteAnswer(int a_id, int u_id);
    public boolean changeVoteAnswer(String typeVote, int a_id, int u_id);
    public boolean deleteByAnswerId(int a_id);
}
