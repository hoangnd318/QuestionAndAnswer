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
public class QuestionFollowConnection {
    
    private static QuestionFollowQuery instance = null;
    
    public static QuestionFollowInterface getQuestionFollowConnection(){
        if (instance == null) {
            instance = new QuestionFollowQuery(new QuestionFollowFacade());
        }
        return instance;
    }
}
