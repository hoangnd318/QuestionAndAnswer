/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

/**
 *
 * @author nguye
 */
public class CreateUrlNotification {

    private static CreateUrlNotification instance = null;

    public static CreateUrlNotification getCreateUrlService() {
        if (instance == null) {
            instance = new CreateUrlNotification();
        }
        return instance;
    }

    private CreateUrlNotification() {
    }

    public String createNotifyAddAnswerUrl(int question_id, String title_raw, int target_answer_id) {
        String url = "/qanda/question/" + question_id + "/" + title_raw + "?answer_id=" + target_answer_id;
        return url;
    }
    
    public String createNotifyAddCommentUrl(int question_id, String title_raw, int target_comment_id) {
        String url = "/qanda/question/" + question_id + "/" + title_raw + "?comment_id=" + target_comment_id;
        return url;
    }
    
    public String createNotifyAddReplyUrl(int thead_id, String title_raw, int target_reply_id) {
        String url = "/threads/" + thead_id + "/" + title_raw + "?reply_id=" + target_reply_id;;
        return url;
    }
}
