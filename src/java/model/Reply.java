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
public class Reply {
    public static String TABLE_NAME = "reply";
    public static String COLUMN_ID = "id";
    public static String COLUMN_CREATE_TIME = "createTime";
    public static String COLUMN_CONTENT = "content";
    public static String COLUMN_THREAD_ID = "thread_id";
    public static String COLUMN_USER_ID = "user_id";
    
    private int id;
    private Timestamp createTime;
    private String content;
    private User author;
    private String convertTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getConvertTime() {
        return convertTime;
    }

    public void setConvertTime(String convertTime) {
        this.convertTime = convertTime;
    }
    
    
}
