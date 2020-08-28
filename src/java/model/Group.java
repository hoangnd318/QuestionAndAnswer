/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author nguye
 */
public class Group {
    
    public static String TABLE_NAME = "groups";
    public static String COLUMN_ID = "id";
    public static String COLUMN_GROUP_CODE = "groupCode";
    public static String COLUMN_GROUP_DEFAULT = "groupDefault";
    public static String COLUMN_NAME = "name";
    public static String COLUMN_DESCRIPTION = "description";
    
    private int id;
    private String groupCode;
    private int groupDefault;
    private String name;
    private String description;
    private ArrayList<Threads> threads;
    private String nameRaw;
    private boolean followedByUser;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    public int getGroupDefault() {
        return groupDefault;
    }

    public void setGroupDefault(int groupDefault) {
        this.groupDefault = groupDefault;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Threads> getThreads() {
        return threads;
    }

    public void setThreads(ArrayList<Threads> threads) {
        this.threads = threads;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNameRaw() {
        return nameRaw;
    }

    public void setNameRaw(String nameRaw) {
        this.nameRaw = nameRaw;
    }

    public boolean getFollowedByUser() {
        return followedByUser;
    }

    public void setFollowedByUser(boolean followedByUser) {
        this.followedByUser = followedByUser;
    }
}
