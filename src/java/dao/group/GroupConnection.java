/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.group;

/**
 *
 * @author nguye
 */
public class GroupConnection {
    private static GroupQuery instance = null;
    
    public static GroupQueryInterface getGroupConnection(){
        if (instance == null) {
            instance = new GroupQuery(new GroupQueryFacade());
        }
        return instance;
    }
}
