/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.answer_vote;

import java.util.ArrayList;

/**
 *
 * @author nguye
 */
public class AnswerVoteQuery implements AnswerVoteInterface{
    AnswerVoteFacade answerVoteFacade;
    
    public AnswerVoteQuery(AnswerVoteFacade answerVoteFacade){
        this.answerVoteFacade = answerVoteFacade;
    }

    @Override
    public int getVoteByAnswerId(int id) {
        return this.answerVoteFacade.getVote(id);
    }

    @Override
    public String checkVoted(int answer_id, int user_id) {
        return this.answerVoteFacade.checkVoted(answer_id, user_id);
    }

    @Override
    public boolean voteAnswer(String typeVote, int a_id, int u_id) {
        return this.answerVoteFacade.vote(AnswerVoteFacade.VOTE_ANSWER, typeVote, a_id, u_id);
    }

    @Override
    public boolean unVoteAnswer(int a_id, int u_id) {
        return this.answerVoteFacade.vote(AnswerVoteFacade.UNVOTE_ANSWER, null, a_id, u_id);
    }

    @Override
    public boolean changeVoteAnswer(String typeVote, int a_id, int u_id) {
        return this.answerVoteFacade.vote(AnswerVoteFacade.CHANGE_VOTE_ANSWER, typeVote, a_id, u_id);
    }

    @Override
    public boolean deleteByAnswerId(int a_id) {
        ArrayList<Object> key = new ArrayList<>();
        key.add(a_id);
        return this.answerVoteFacade.delete(AnswerVoteFacade.DELETE_BY_ANSWER_ID, key);
    }
    
    
}
