/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.question_tag;

/**
 *
 * @author nguye
 */
public class QuestionTagConnection {
    
    private static QuestionTagQuery instance = null;
    
    public static QuestionTagInterface getQuestionTagConnection(){
        if (instance == null) {
            instance = new QuestionTagQuery(new QuestionTagFacade());
        }
        return instance;
    }
}
