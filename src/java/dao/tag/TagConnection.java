/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.tag;

/**
 *
 * @author nguye
 */
public class TagConnection {
    
    private static TagQuery instance = null;
    
    public static TagQueryInterface getTagConnection(){
        if (instance == null) {
            instance = new TagQuery(new TagQueryFacade());
        }
        return instance;
    }
}
