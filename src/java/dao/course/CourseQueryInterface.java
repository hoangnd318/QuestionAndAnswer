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
public interface CourseQueryInterface {
    public Course findById(int id);
    public ArrayList<Course> findAll();
}
