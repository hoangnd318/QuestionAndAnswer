/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author nguye
 */
public class QuestionFollow {
    public static String ROLE_AUTHOR = "author_q";
    public static String ROLE_GUEST = "guest";
    
    public static String TABLE_NAME = "question_follow";
    public static String COLUMN_ID = "id";
    public static String COLUMN_ROLE = "role";
    public static String COLUMN_FOLLOW = "follow";
    public static String COLUMN_QUESTION_ID = "question_id";
    public static String COLUMN_USER_ID = "user_id";
    
    private int id;
    private String role;
    private int follow;
    private User user;
    private Question question;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getFollow() {
        return follow;
    }

    public void setFollow(int follow) {
        this.follow = follow;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

   
    
    
}
