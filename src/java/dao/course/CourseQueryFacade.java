/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.course;

import dao.dbconnection.DBConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Course;

/**
 *
 * @author nguye
 */
public class CourseQueryFacade {

    public static int FIND_BY_ID = 1;
    public static int FIND_ALL = 2;

    private PreparedStatement preparedStatement;

    public CourseQueryFacade() {
        this.preparedStatement = null;
    }

    public ArrayList<Course> find(int type, Object key) {
        ArrayList<Course> courses = new ArrayList<>();
        String sql_query = "";
        if (type == CourseQueryFacade.FIND_BY_ID) {
            sql_query = "SELECT * FROM " + Course.TABLE_NAME + " WHERE " + Course.COLUMN_ID + "=?";
        } else if (type == CourseQueryFacade.FIND_ALL) {
            sql_query = "SELECT * FROM course";
        }
        try {
            preparedStatement = DBConnection.getInstance().getConnection().prepareStatement(sql_query);
            if (type == CourseQueryFacade.FIND_BY_ID) {
                int id = (int) key;
                preparedStatement.setInt(1, id);
            }
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Course course = new Course();
                course.setId(resultSet.getInt(Course.COLUMN_ID));
                course.setName(resultSet.getString(Course.COLUMN_NAME));
                courses.add(course);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CourseQueryFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return courses;
    }
}
