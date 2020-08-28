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
public class Tag {

    public static String TABLE_NAME = "tag";
    public static String COLUMN_ID = "id";
    public static String COLUMN_NAME = "name";
    public static String COLUMN_CREATE_TIME = "create_time";

    private int id;
    private String name;
    private Timestamp createTime;
    private int total;
    private String convertCreateTime;

    public Tag() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getConvertCreateTime() {
        return convertCreateTime;
    }

    public void setConvertCreateTime(String convertCreateTime) {
        this.convertCreateTime = convertCreateTime;
    }
}
