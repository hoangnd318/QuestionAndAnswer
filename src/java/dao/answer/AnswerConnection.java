/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.answer;

/**
 *
 * @author nguye
 */
public class AnswerConnection {
    
    private static AnswerQuery instance = null;
    
    public static AnswerQueryInterface getAnswerConnection(){
        if (instance == null) {
            instance = new AnswerQuery(new AnswerQueryFacade());
        }
        return instance;        
    }
}
