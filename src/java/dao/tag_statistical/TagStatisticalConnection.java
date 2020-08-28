/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.tag_statistical;

/**
 *
 * @author nguye
 */
public class TagStatisticalConnection {
     
    private static TagStatisticalQuery instance = null;
    
    public static TagStatisticalQueryInterface getTagStatisticalConnection(){
        if (instance == null) {
            instance = new TagStatisticalQuery(new TagStatisticalQueryFacade());
        }
        return instance;
    }
}
