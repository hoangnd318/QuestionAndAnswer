/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.thread_follow;

/**
 *
 * @author nguye
 */
public class ThreadFollowConnection {
    private static ThreadFollowQuery instance = null;

    public static ThreadFollowInterface getAnswerFollowConnection() {
        if (instance == null) {
            instance = new ThreadFollowQuery(new ThreadFollowFacade());
        }
        return instance;
    }
}
