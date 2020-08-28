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
public class TagStatistical {

    public static String TABLE_NAME = "tag_statistical";
    public static String COLUMN_ID = "id";
    public static String COLUMN_SEARCH_TIME = "search_time";
    public static String COLUMN_TAG_ID = "tag_id";

    private int id;
    private Tag tag;
    private Timestamp searchTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    public Timestamp getSearchTime() {
        return searchTime;
    }

    public void setSearchTime(Timestamp searchTime) {
        this.searchTime = searchTime;
    }

}
