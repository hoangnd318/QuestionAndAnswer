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
public class AnswerVoteConnection {
    
    private static AnswerVoteQuery instance = null;
    
    public static AnswerVoteInterface getAnswerVoteConnection(){
        if (instance == null) {
            instance = new AnswerVoteQuery(new AnswerVoteFacade());
        }
        return instance;
    } 
}
