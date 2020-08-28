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
public class UserStatistical {
    private int countQuestion;
    private int countAnswer;
    private int countBestAnswer;

    public int getCountQuestion() {
        return countQuestion;
    }

    public void setCountQuestion(int countQuestion) {
        this.countQuestion = countQuestion;
    }

    public int getCountAnswer() {
        return countAnswer;
    }

    public void setCountAnswer(int countAnswer) {
        this.countAnswer = countAnswer;
    }

    public int getCountBestAnswer() {
        return countBestAnswer;
    }

    public void setCountBestAnswer(int countBestAnswer) {
        this.countBestAnswer = countBestAnswer;
    }
    
    
}
