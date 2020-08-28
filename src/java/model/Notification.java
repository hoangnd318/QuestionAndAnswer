/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dao.notification.NotificationConnection;
import java.sql.Timestamp;

/**
 *
 * @author nguye
 */
public class Notification {

    public static int ANSWER_QUESTION = 1;
    public static int COMMENT_ANSWER = 2;

    public static String SEEN = "seen";
    public static String UN_SEEN = "un_seen";

    public static String TABLE_NAME = "notification";
    public static String COLUMN_ID = "id";
    public static String COLUMN_STATUS = "status";
    public static String COLUMN_URL = "url";
    public static String COLUMN_CONTENT = "content";
    public static String COLUMN_CREATE_TIME = "create_time";
    public static String COLUMN_MARK_SEEN = "mark_seen";
    public static String COLUMN_RECIPIENT_ID = "recipient_id";

    private int id;
    private String status;
    private String url;
    private String content;
    private User recipient;
    private Timestamp createTime;
    private String convertTime;
    private String markSeen;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getRecipient() {
        return recipient;
    }

    public void setRecipient(User recipient) {
        this.recipient = recipient;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getConvertTime() {
        return convertTime;
    }

    public void setConvertTime(String convertTime) {
        this.convertTime = convertTime;
    }

    public String getMarkSeen() {
        return markSeen;
    }

    public void setMarkSeen(String markSeen) {
        this.markSeen = markSeen;
    }
    
    
}
