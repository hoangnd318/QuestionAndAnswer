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
public class QuestionVote {

    public static String TABLE_NAME = "question_vote";
    public static String COLUMN_ID = "id";
    public static String COLUMN_TYPE = "type";
    public static String COLUMN_USER_ID = "user_id";
    public static String COLUMN_QUESTION_ID = "question_id";
    public static String UN_VOTE_TYPE = "un_vote";
    public static String VOTE_UP_TYPE = "vote_up";
    public static String VOTE_DOWN_TYPE = "vote_down";

    private int id;
    private String type;
    private User userVote;
    private Question question;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public User getUserVote() {
        return userVote;
    }

    public void setUserVote(User userVote) {
        this.userVote = userVote;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

}
