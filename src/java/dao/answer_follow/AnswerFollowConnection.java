/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.answer_follow;

/**
 *
 * @author nguye
 */
public class AnswerFollowConnection {

    private static AnswerFollowQuery instance = null;

    public static AnswerFollowInterface getAnswerFollowConnection() {
        if (instance == null) {
            instance = new AnswerFollowQuery(new AnswerFollowFacade());
        }
        return instance;
    }
}
