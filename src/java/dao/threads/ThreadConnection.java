/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.threads;

/**
 *
 * @author nguye
 */
public class ThreadConnection {
    private static ThreadQuery instance = null;
    
    public static ThreadQueryInterface getThreadConnection(){
        if (instance == null) {
            instance = new ThreadQuery(new ThreadQueryFacade());
        }
        return instance;
    }
}
