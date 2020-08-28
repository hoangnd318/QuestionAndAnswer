/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.reply;

/**
 *
 * @author nguye
 */
public class ReplyConnection {
    private static ReplyQuery instance = null;
    
    public static ReplyQueryInterface getReplyConnection(){
        if (instance == null) {
            instance = new ReplyQuery(new ReplyQueryFacade());
        }
        return instance;
    }
}
