/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.course;

/**
 *
 * @author nguye
 */
public class CourseConnection {    
    
    private static CourseQuery instance = null;
    
    public static CourseQueryInterface getCourseConnection(){
        if (instance == null) {
            instance = new CourseQuery(new CourseQueryFacade());
        }
        return instance;
    }
}
