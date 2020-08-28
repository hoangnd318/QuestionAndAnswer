/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.comment;

/**
 *
 * @author nguye
 */
public class CommentConnection {
    
    private static CommentQuery instance = null;
    
    public static CommentQueryInterface getCommentConnection(){
        if (instance == null) {
            instance = new CommentQuery(new CommentQueryFacade());
        }
        return instance;
    }
}
