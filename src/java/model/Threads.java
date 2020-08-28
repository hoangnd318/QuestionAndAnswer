/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Timestamp;
import java.util.ArrayList;

/**
 *
 * @author nguye
 */
public class Threads {
    public static String TABLE_NAME = "thread";
    public static String COLUMN_ID = "id";
    public static String COLUMN_CREATE_TIME = "createTime";
    public static String COLUMN_CONTENT = "content";
    public static String COLUMN_GROUP_ID = "group_id";
    public static String COLUMN_USER_ID = "user_id";
    public static String COLUMN_TITLE = "title";
    public static String COLUMN_TITLE_RAW = "title_raw";
    
    private int id;
    private Timestamp createTime;
    private String content;
    private User author;
    private ArrayList<Reply> listReply;
    private String title;
    private String titleRaw;
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

    public ArrayList<Reply> getListReply() {
        return listReply;
    }

    public void setListReply(ArrayList<Reply> listReply) {
        this.listReply = listReply;
    }

    public String getTitleRaw() {
        return titleRaw;
    }

    public void setTitleRaw(String titleRaw) {
        this.titleRaw = titleRaw;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getConvertTime() {
        return convertTime;
    }

    public void setConvertTime(String convertTime) {
        this.convertTime = convertTime;
    }
    
}
