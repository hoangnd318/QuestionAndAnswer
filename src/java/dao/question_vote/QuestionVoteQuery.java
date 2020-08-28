/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.question_vote;

import java.util.ArrayList;

/**
 *
 * @author nguye
 */
public class QuestionVoteQuery implements QuestionVoteInterface{
    private QuestionVoteFacade questionVoteFacade;
    
    public QuestionVoteQuery(QuestionVoteFacade questionVoteFacade){
        this.questionVoteFacade = questionVoteFacade;
    }
    
    @Override
    public int getVoteByQuestionId(int id) {
        return this.questionVoteFacade.getVote(id);
    } 

    @Override
    public String checkVoted(int question_id, int user_id) {
        return this.questionVoteFacade.checkVoted(question_id, user_id);
    }

    @Override
    public boolean voteQuestion(String typeVote, int q_id, int u_id) {
        return this.questionVoteFacade.vote(QuestionVoteFacade.VOTE_QUESTION, typeVote, q_id, u_id);
    }

    @Override
    public boolean unVoteQuestion(int q_id, int u_id) {
        return this.questionVoteFacade.vote(QuestionVoteFacade.UNVOTE_QUESTION, null, q_id, u_id);
    }

    @Override
    public boolean changeVoteQuestion(String typeVote, int q_id, int u_id) {
        return this.questionVoteFacade.vote(QuestionVoteFacade.CHANGE_VOTE_QUESTION, typeVote, q_id, u_id);
    }

    @Override
    public boolean deleteByQuestionId(int q_id) {
        ArrayList<Object> key = new ArrayList<>();
        key.add(q_id);
        return this.questionVoteFacade.delete(QuestionVoteFacade.DELETE_BY_QUESTION_ID, key);
    }
}
