/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dao.question.QuestionConnection;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 *
 * @author nguye
 */
public class Question {

    public static String TABLE_NAME = "question";
    public static String COLUMN_ID = "id";
    public static String COLUMN_TITLE = "title";
    public static String COLUMN_TITLE_RAW = "title_raw";
    public static String COLUMN_CONTENT = "content";
    public static String COLUMN_CONTENT_RAW = "content_raw";
    public static String COLUMN_CREATE_TIME = "create_time";
    public static String COLUMN_USER_ID = "user_id";
    public static String COLUMN_POINT_PRIOITY = "point_priority";
    public static String COLUMN_VIEW = "view";

    private int id;
    private String title;
    private String title_raw;
    private String content;
    private String content_raw;
    private Timestamp createTime;
    private ArrayList<Answer> answers;
    private ArrayList<Tag> tags;
    private User author;
    private int views;
    private int vote;
    private int countAnswer;
    private int pointPriority;
    private String votedByUser;
    private int hasBestAnswer;
    private String convertTime;

    public Question() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public ArrayList<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(ArrayList<Answer> answers) {
        this.answers = answers;
    }

    public ArrayList<Tag> getTags() {
        return tags;
    }

    public void setTags(ArrayList<Tag> tags) {
        this.tags = tags;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public int getCountAnswer() {
        return countAnswer;
    }

    public void setCountAnswer(int countAnswer) {
        this.countAnswer = countAnswer;
    }

    public int getVote() {
        return vote;
    }

    public void setVote(int vote) {
        this.vote = vote;
    }

    public int getPointPriority() {
        return pointPriority;
    }

    public void setPointPriority(int pointPriority) {
        this.pointPriority = pointPriority;
    }

    public String getTitle_raw() {
        return title_raw;
    }

    public void setTitle_raw(String title_raw) {
        this.title_raw = title_raw;
    }

    public String getVotedByUser() {
        return votedByUser;
    }

    public void setVotedByUser(String votedByUser) {
        this.votedByUser = votedByUser;
    }

    public String getContent_raw() {
        return content_raw;
    }

    public void setContent_raw(String content_raw) {
        this.content_raw = content_raw;
    }

    public int getHasBestAnswer() {
        return hasBestAnswer;
    }

    public void setHasBestAnswer(int hasBestAnswer) {
        this.hasBestAnswer = hasBestAnswer;
    }

    public String getConvertTime() {
        return convertTime;
    }

    public void setConvertTime(String convertTime) {
        this.convertTime = convertTime;
    }
    
    
}
