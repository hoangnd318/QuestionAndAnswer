/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.sql.ResultSet;
import model.AnswerFollow;
import model.QuestionFollow;

/**
 *
 * @author nguye
 */
public class CreateContentNotification {

    private static CreateContentNotification instance = null;

    public static CreateContentNotification getCreateContentService() {
        if (instance == null) {
            instance = new CreateContentNotification();
        }
        return instance;
    }

    private CreateContentNotification() {
    }

    public String createNotifyAddAnswerContent(String fullName, String role) {
        String content = "";
        if (role.compareTo(QuestionFollow.ROLE_AUTHOR) == 0) {
            content = "<span>" + fullName + "</span>" + " đã trả lời câu hỏi của bạn.";
        } else if (role.compareTo(QuestionFollow.ROLE_GUEST) == 0) {
            content = "<span>" + fullName + "</span>" + " đã trả lời trong một câu hỏi bạn theo dõi.";
        }
        return content;
    }
    
    public String createNotifyAddCommentContent(String fullName, String role) {
        String content = "";
        if (role.compareTo(AnswerFollow.ROLE_AUTHOR) == 0) {
            content = "<span>" + fullName + "</span>" + " đã bình luận câu trả lời của bạn trong một câu hỏi.";
        } else if (role.compareTo(AnswerFollow.ROLE_GUEST) == 0) {
            content = "<span>" + fullName + "</span>" + " đã bình luận trong một câu trả lời bạn theo dõi.";
        } else if(role.compareTo(AnswerFollow.ROLE_AUTHOR_QUESTION) == 0){
            content = "<span>" + fullName + "</span>" + " đã bình luận trong câu hỏi của bạn.";
        }
        return content;
    }
}
