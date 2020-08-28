/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.group_follow;

/**
 *
 * @author nguye
 */
public class GroupFollowConnection {
    private static GroupFollowQuery instance = null;
    
    public static GroupFollowInterface getAnswerConnection(){
        if (instance == null) {
            instance = new GroupFollowQuery(new GroupFollowFacade());
        }
        return instance;        
    }
}
