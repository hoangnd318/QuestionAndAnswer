/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.notification;

import java.util.ArrayList;
import model.Answer;
import model.Comment;
import model.Notification;
import model.Reply;
import model.Threads;

/**
 *
 * @author nguye
 */
public interface NotificationQueryInterface {
    public ArrayList<Notification> findById(int recipient_id);
    public ArrayList<Notification> findByIdScroll(int r_id, int n_id);
    public int getCountUnseenNotification(int user_id);
    public boolean addNewNotification(Notification n);
    public boolean sendNotificationNewAnswer(Answer answer, int question_id);
    public boolean sendNotificationWhenComment(Comment comment, int answer_id);
    public boolean sendNotificationWhenReply(Reply reply, int thread_id);
    public boolean sendNotificationWhenThread(Threads thread, int group_id);
    public boolean changeStatus(Notification n);
    public boolean markSeen(int user_id);
}
