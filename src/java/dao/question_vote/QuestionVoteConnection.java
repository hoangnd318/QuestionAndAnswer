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
public class QuestionVoteConnection {
    
    private static QuestionVoteQuery instance = null;
    
    public static QuestionVoteInterface getQuestionVoteConnection(){
        if (instance == null) {
            instance = new QuestionVoteQuery(new QuestionVoteFacade());
        }
        return instance;
    }
}
