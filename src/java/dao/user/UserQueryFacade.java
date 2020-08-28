/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.user;

import dao.course.CourseConnection;
import dao.department.DepartmentConnection;
import dao.person.PersonConnection;
import dao.user_account.UserAccountConnection;
import dao.dbconnection.DBConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Person;
import model.User;

/**
 *
 * @author nguye
 */
public class UserQueryFacade {

    //
    public static int FIND_BY_ID = 1;
    public static int FIND_BY_USER_ACCOUNT_ID = 2;

    //
    public static int UPDATE_POINT = 1;
    public static int CREATE_NEW = 2;
    public static int UPDATE_PROFILE_SV = 3;

    private PreparedStatement preparedStatement;

    public UserQueryFacade() {
        this.preparedStatement = null;
    }

    public ArrayList<User> find(int type, ArrayList<Object> key) {
        ArrayList<User> users = new ArrayList<>();
        String sql_query = "";
        if (type == UserQueryFacade.FIND_BY_ID) {
            sql_query = "SELECT * FROM " + User.TABLE_NAME + " WHERE " + User.COLUMN_ID_USER + " = ?";
        } else if (type == UserQueryFacade.FIND_BY_USER_ACCOUNT_ID) {
            sql_query = "SELECT * FROM " + User.TABLE_NAME + " WHERE " + User.COLUMN_USER_ACCOUNT_ID + " = ?";
        }
        try {
            preparedStatement = DBConnection.getInstance().getConnection().prepareStatement(sql_query);
            if (type == UserQueryFacade.FIND_BY_ID) {
                int id = (int) key.get(0);
                preparedStatement.setInt(1, id);
            } else if (type == UserQueryFacade.FIND_BY_USER_ACCOUNT_ID) {
                int id = (int) key.get(0);
                preparedStatement.setInt(1, id);
            }
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setIdUser(resultSet.getInt(User.COLUMN_ID_USER));
                user.setPointQA(resultSet.getInt(User.COLUMN_POINT_QA));
                user.setCreateTime(resultSet.getTimestamp(User.COLUMN_CREATE_TIME));
                user.setStudentCode(resultSet.getString(User.COLUMN_STUDENT_CODE));
                user.setType(resultSet.getString(User.COLUMN_TYPE));
                user.setAccount(UserAccountConnection.getUserAccountConnection().findById(resultSet.getInt(User.COLUMN_USER_ACCOUNT_ID)));
                user.setDepartment(DepartmentConnection.getDepartmentConnection().findById(resultSet.getInt(User.COLUMN_DEPARTMENT_ID)));
                user.setCourse(CourseConnection.getCourseConnection().findById(resultSet.getInt(User.COLUMN_COURSE_ID)));
                Person person = PersonConnection.getPersonConnection().findById(resultSet.getInt(User.COLUMN_PERSON_ID));
                user.setId(person.getId());
                user.setFirstname(person.getFirstname());
                user.setMidname(person.getMidname());
                user.setLastname(person.getLastname());
                user.setAddress(person.getAddress());
                user.setGender(person.getGender());
                user.setBirthday(person.getBirthday());
                users.add(user);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserQueryFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return users;
    }

    public int update(int type, User user) {
        int user_id = 0;
        String sql_query = "";
        if (type == UserQueryFacade.UPDATE_POINT) {
            sql_query = "UPDATE "
                    + User.TABLE_NAME
                    + " SET "
                    + User.COLUMN_POINT_QA + " = ?"
                    + " WHERE "
                    + User.COLUMN_ID_USER + " = ?";
        } else if (type == UserQueryFacade.CREATE_NEW) {
            sql_query = "INSERT INTO "
                    + User.TABLE_NAME
                    + "(" + User.COLUMN_POINT_QA + ","
                    + User.COLUMN_STUDENT_CODE + ","
                    + User.COLUMN_TYPE + ","
                    + User.COLUMN_DEPARTMENT_ID + ","
                    + User.COLUMN_COURSE_ID + ","
                    + User.COLUMN_USER_ACCOUNT_ID + ","
                    + User.COLUMN_PERSON_ID + ") VALUES(?,?,?,?,?,?,?)";
        } else if (type == UserQueryFacade.UPDATE_PROFILE_SV) {
            sql_query = "UPDATE user SET student_code=?,department_id=?,course_id=? WHERE id_user=?";
        }
        try {
            preparedStatement = DBConnection.getInstance().getConnection().prepareStatement(sql_query, Statement.RETURN_GENERATED_KEYS);
            if (type == UserQueryFacade.UPDATE_POINT) {
                preparedStatement.setInt(1, user.getPointQA());
                preparedStatement.setInt(2, user.getIdUser());
                preparedStatement.executeUpdate();
            } else if (type == UserQueryFacade.CREATE_NEW) {
                preparedStatement.setInt(1, user.getPointQA());
                preparedStatement.setString(2, user.getStudentCode());
                preparedStatement.setString(3, user.getType());
                preparedStatement.setInt(4, user.getDepartment().getId());
                preparedStatement.setInt(5, user.getCourse().getId());
                preparedStatement.setInt(6, user.getAccount().getIdUserAccount());
                preparedStatement.setInt(7, user.getId());
                preparedStatement.executeUpdate();
                ResultSet rs = preparedStatement.getGeneratedKeys();
                if (rs.next()) {
                    user_id = rs.getInt(1);
                    user.setIdUser(user_id);
                }
            } else if (type == UserQueryFacade.UPDATE_PROFILE_SV) {
                preparedStatement.setString(1, user.getStudentCode());
                preparedStatement.setInt(2, user.getDepartment().getId());
                preparedStatement.setInt(3, user.getCourse().getId());
                preparedStatement.setInt(4, user.getIdUser());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserQueryFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user_id;
    }
}
