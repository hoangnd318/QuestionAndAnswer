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
public class NotificationQuery implements NotificationQueryInterface {

    private NotificationQueryFacade nqf;

    public NotificationQuery(NotificationQueryFacade nqf) {
        this.nqf = nqf;
    }

    @Override
    public boolean addNewNotification(Notification n) {
        return this.nqf.update(NotificationQueryFacade.ADD_NEW_NOTIFICATION, n, 0);
    }

    @Override
    public boolean sendNotificationNewAnswer(Answer answer, int question_id) {
        return this.nqf.sendNotificationWhenAnswer(answer, question_id);
    }

    @Override
    public boolean sendNotificationWhenComment(Comment comment, int answer_id) {
        return this.nqf.sendNotificationWhenComment(comment, answer_id);
    }

    @Override
    public ArrayList<Notification> findById(int recipient_id) {
        ArrayList<Object> key = new ArrayList<>();
        key.add(recipient_id);
        return this.nqf.find(NotificationQueryFacade.FIND_ALL, key);
    }

    @Override
    public int getCountUnseenNotification(int user_id) {
        return this.nqf.countNotification(user_id);
    }

    @Override
    public boolean changeStatus(Notification n) {
        return this.nqf.update(NotificationQueryFacade.CHANGE_STATUS, n, 0);
    }

    @Override
    public boolean markSeen(int user_id) {
        return this.nqf.update(NotificationQueryFacade.MARK_SEEN, null, user_id);
    }

    @Override
    public ArrayList<Notification> findByIdScroll(int r_id, int n_id) {
        ArrayList<Object> key = new ArrayList<>();
        key.add(r_id);
        key.add(n_id);
        return this.nqf.find(NotificationQueryFacade.FIND_SCROLL, key);
    }

    @Override
    public boolean sendNotificationWhenReply(Reply reply, int thread_id) {
        return nqf.sendNotificationWhenReply(reply, thread_id);
    }

    @Override
    public boolean sendNotificationWhenThread(Threads thread, int group_id) {
        return nqf.sendNotificationWhenThread(thread, group_id);
    }

}
