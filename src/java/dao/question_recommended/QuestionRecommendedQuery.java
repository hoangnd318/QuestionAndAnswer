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
public class QuestionRecommendedQuery implements QuestionRecommendedQueryInterface{

    QuestionRecommendedQueryFacade questionRecommendedQueryFacade;

    public QuestionRecommendedQuery(QuestionRecommendedQueryFacade questionRecommendedQueryFacade) {
        this.questionRecommendedQueryFacade = questionRecommendedQueryFacade;
    }
    
    @Override
    public void updateRecommendedQuestion(int id_q_root, int id_q_recommended) {
        this.questionRecommendedQueryFacade.update(id_q_root, id_q_recommended);
    }
    
}
