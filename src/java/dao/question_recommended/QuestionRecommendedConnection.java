/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.question_recommended;

/**
 *
 * @author nguye
 */
public class QuestionRecommendedConnection {
    
    private static QuestionRecommendedQuery instance = null;
    
    public static QuestionRecommendedQueryInterface getQuestionRecommendedConnection(){
        if (instance == null) {
            instance = new QuestionRecommendedQuery(new QuestionRecommendedQueryFacade());
        }
        return instance;
    }
    
}
