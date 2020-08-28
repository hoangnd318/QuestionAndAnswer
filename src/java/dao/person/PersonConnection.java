/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.person;

/**
 *
 * @author nguye
 */
public class PersonConnection {
    
    private static PersonQuery instance = null;
    
    public static PersonQueryInterface getPersonConnection(){
        if (instance == null) {
            instance = new PersonQuery(new PersonQueryFacade());
        }
        return instance;
    }
}
