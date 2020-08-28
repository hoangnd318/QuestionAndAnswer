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
public class ThreadFollow {
    
    public static String ROLE_AUTHOR = "author_t";
    public static String ROLE_GUEST = "guest";
    
    public static String TABLE_NAME = "thread_follow";
    public static String COLUMN_ID = "id";
    public static String COLUMN_ROLE = "role";
    public static String COLUMN_THREAD_ID = "thread_id";
    public static String COLUMN_USER_ID = "user_id";
    
    private int id;
    private String role;
    private User user;
    private Thread thread;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Thread getThread() {
        return thread;
    }

    public void setThread(Thread thread) {
        this.thread = thread;
    }
    
    
}
