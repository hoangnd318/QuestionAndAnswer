/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.question;

/**
 *
 * @author nguye
 */
public class QuestionConnection {
    
    private static QuestionQuery instance = null;
    
    public static QuestionQueryInterface getQuestionConnection(){
        if (instance == null) {
            instance = new QuestionQuery(new QuestionQueryFacade());
        }
        return instance;
    }
}
