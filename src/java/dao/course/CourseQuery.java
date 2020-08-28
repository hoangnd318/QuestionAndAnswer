/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.course;

import java.util.ArrayList;
import model.Course;

/**
 *
 * @author nguye
 */
public class CourseQuery implements CourseQueryInterface{
    private CourseQueryFacade courseQueryFacade;
    
    public CourseQuery(CourseQueryFacade courseQueryFacade){
        this.courseQueryFacade = courseQueryFacade;
    }
    
    private static CourseQueryInterface courseQueryInterface;
    
    public static CourseQueryInterface getCourseQueryInterface(){
        return courseQueryInterface = new CourseQuery(new CourseQueryFacade());
    }

    @Override
    public Course findById(int id) {
        return this.courseQueryFacade.find(CourseQueryFacade.FIND_BY_ID, id).get(0);
    }

    @Override
    public ArrayList<Course> findAll() {
        return courseQueryFacade.find(CourseQueryFacade.FIND_ALL, 0);
    }
    
}
