/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Timestamp;

/**
 *
 * @author nguye
 */
public class User extends Person {

    public static String TABLE_NAME = "user";
    public static String COLUMN_ID_USER = "id_user";
    public static String COLUMN_POINT_QA = "point_qa";
    public static String COLUMN_CREATE_TIME = "create_time";
    public static String COLUMN_STUDENT_CODE = "student_code";
    public static String COLUMN_TYPE = "type";
    public static String COLUMN_DEPARTMENT_ID = "department_id";
    public static String COLUMN_COURSE_ID = "course_id";
    public static String COLUMN_USER_ACCOUNT_ID = "user_account_id";
    public static String COLUMN_PERSON_ID = "person_id";

    private int idUser;
    private int pointQA;
    private Timestamp createTime;
    private Course course;
    private Department department;
    private String studentCode;
    private String type;
    private UserAccount account;

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getPointQA() {
        return pointQA;
    }

    public void setPointQA(int pointQA) {
        this.pointQA = pointQA;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getStudentCode() {
        return studentCode;
    }

    public void setStudentCode(String studentCode) {
        this.studentCode = studentCode;
    }

    public UserAccount getAccount() {
        return account;
    }

    public void setAccount(UserAccount account) {
        this.account = account;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    
}
