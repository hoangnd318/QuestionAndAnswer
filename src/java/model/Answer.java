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
public class Answer {

    public static String TABLE_NAME = "answer";
    public static String COLUMN_ID = "id";
    public static String COLUMN_CONTENT = "content";
    public static String COLUMN_CONTENT_RAW = "content_raw";
    public static String COLUMN_CREATE_TIME = "create_time";
    public static String COLUMN_BEST_ANSWER = "best_answer";
    public static String COLUMN_QUESTION_ID = "question_id";
    public static String COLUMN_USER_ID = "user_id";

    private int id;
    private String content;
    private String content_raw;
    private User author;
    private int bestAnswer;
    private Timestamp createTime;
    private ArrayList<Comment> comments;
    private int vote;
    private String votedByUser;
    private String convertTime;
    private Question question;

    public Answer() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public int getBestAnswer() {
        return bestAnswer;
    }

    public void setBestAnswer(int bestAnswer) {
        this.bestAnswer = bestAnswer;
    }

    public int getVote() {
        return vote;
    }

    public void setVote(int vote) {
        this.vote = vote;
    }

    public String getVotedByUser() {
        return votedByUser;
    }

    public void setVotedByUser(String votedByUser) {
        this.votedByUser = votedByUser;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }

    public String getContent_raw() {
        return content_raw;
    }

    public void setContent_raw(String content_raw) {
        this.content_raw = content_raw;
    }

    public String getConvertTime() {
        return convertTime;
    }

    public void setConvertTime(String convertTime) {
        this.convertTime = convertTime;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    
    
}
