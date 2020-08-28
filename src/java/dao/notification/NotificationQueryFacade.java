/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.notification;

import dao.dbconnection.DBConnection;
import dao.question.QuestionConnection;
import dao.threads.ThreadConnection;
import dao.user.UserConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Answer;
import model.Comment;
import model.Notification;
import model.Question;
import model.AnswerFollow;
import model.QuestionFollow;
import model.Reply;
import model.ThreadFollow;
import model.Threads;
import model.User;
import service.ConvertTimestamp;
import service.CreateContentNotification;
import service.CreateUrlNotification;

/**
 *
 * @author nguye
 */
public class NotificationQueryFacade {

    public static int FIND_ALL = 1;
    public static int FIND_SCROLL = 2;

    public static int ADD_NEW_NOTIFICATION = 1;
    public static int CHANGE_STATUS = 2;
    public static int MARK_SEEN = 3;

    private PreparedStatement preparedStatement;

    public NotificationQueryFacade() {
        this.preparedStatement = null;
    }

    public ArrayList<Notification> find(int type, ArrayList<Object> key) {
        ArrayList<Notification> notifications = new ArrayList<>();
        String sql_query = "";
        if (type == NotificationQueryFacade.FIND_ALL) {
            sql_query = "SELECT * FROM "
                    + Notification.TABLE_NAME
                    + " WHERE "
                    + Notification.COLUMN_RECIPIENT_ID
                    + " = ? "
                    + "ORDER BY "
                    + Notification.COLUMN_CREATE_TIME
                    + " DESC"
                    + " LIMIT 0,15";
        } else if (type == NotificationQueryFacade.FIND_SCROLL) {
            sql_query = "SELECT * FROM "
                    + Notification.TABLE_NAME
                    + " WHERE "
                    + Notification.COLUMN_RECIPIENT_ID
                    + " = ? && "
                    + Notification.COLUMN_ID
                    + " < ? "
                    + " ORDER BY " 
                    + Notification.COLUMN_ID 
                    + " DESC "
                    + "LIMIT 0,10";
        }

        try {
            preparedStatement = DBConnection.getInstance().getConnection().prepareStatement(sql_query);
            if (type == NotificationQueryFacade.FIND_ALL) {
                preparedStatement.setInt(1, (int) key.get(0));
            }else if(type == NotificationQueryFacade.FIND_SCROLL){
                preparedStatement.setInt(1, (int) key.get(0));
                preparedStatement.setInt(2, (int) key.get(1));
            }
            
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Notification n = new Notification();
                n.setId(resultSet.getInt(Notification.COLUMN_ID));
                n.setStatus(resultSet.getString(Notification.COLUMN_STATUS));
                n.setUrl(resultSet.getString(Notification.COLUMN_URL));
                n.setContent(resultSet.getString(Notification.COLUMN_CONTENT));
                n.setCreateTime(resultSet.getTimestamp(Notification.COLUMN_CREATE_TIME));
                n.setRecipient(UserConnection.getUserConnection().findById((int) key.get(0)));
                //convert time
                ConvertTimestamp ct = new ConvertTimestamp();
                String timeConvert = ct.convertTimeNotify(resultSet.getTimestamp(Notification.COLUMN_CREATE_TIME));
                n.setConvertTime(timeConvert);
                n.setMarkSeen(resultSet.getString(Notification.COLUMN_MARK_SEEN));
                notifications.add(n);
            }
        } catch (SQLException ex) {
            Logger.getLogger(NotificationQueryFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return notifications;
    }

    public int countNotification(int user_id) {
        int count = 0;
        String sql_query = "SELECT * FROM "
                + Notification.TABLE_NAME
                + " WHERE "
                + Notification.COLUMN_RECIPIENT_ID
                + " = ? &&"
                + Notification.COLUMN_MARK_SEEN
                + " = 'un_seen'";
        try {
            preparedStatement = DBConnection.getInstance().getConnection().prepareStatement(sql_query);
            preparedStatement.setInt(1, user_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.last()) {
                count = resultSet.getRow();
            }
        } catch (SQLException ex) {
            Logger.getLogger(NotificationQueryFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }

    public boolean update(int type, Notification notification, int user_id) {
        boolean status = true;
        String sql_query = "";
        if (type == NotificationQueryFacade.ADD_NEW_NOTIFICATION) {
            sql_query = "INSERT INTO "
                    + Notification.TABLE_NAME
                    + "(" + Notification.COLUMN_URL + ","
                    + Notification.COLUMN_CONTENT + ","
                    + Notification.COLUMN_RECIPIENT_ID + ") VALUES(?,?,?)";
        } else if (type == NotificationQueryFacade.CHANGE_STATUS) {
            sql_query = "UPDATE "
                    + Notification.TABLE_NAME
                    + " SET "
                    + Notification.COLUMN_STATUS + " = ?"
                    + " WHERE "
                    + Notification.COLUMN_ID + " = ?";
        } else if (type == NotificationQueryFacade.MARK_SEEN) {
            sql_query = "UPDATE "
                    + Notification.TABLE_NAME
                    + " SET "
                    + Notification.COLUMN_MARK_SEEN + " = ?"
                    + " WHERE "
                    + Notification.COLUMN_MARK_SEEN + " = ? && "
                    + Notification.COLUMN_RECIPIENT_ID + " = ?";
        }
        try {
            preparedStatement = DBConnection.getInstance().getConnection().prepareStatement(sql_query);
            if (type == NotificationQueryFacade.ADD_NEW_NOTIFICATION) {
                preparedStatement.setString(1, notification.getUrl());
                preparedStatement.setString(2, notification.getContent());
                preparedStatement.setInt(3, notification.getRecipient().getIdUser());
                preparedStatement.executeUpdate();
            } else if (type == NotificationQueryFacade.CHANGE_STATUS) {
                preparedStatement.setString(1, Notification.SEEN);
                preparedStatement.setInt(2, notification.getId());
                preparedStatement.executeUpdate();
            } else if (type == NotificationQueryFacade.MARK_SEEN) {
                preparedStatement.setString(1, Notification.SEEN);
                preparedStatement.setString(2, Notification.UN_SEEN);
                preparedStatement.setInt(3, user_id);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException ex) {
            Logger.getLogger(NotificationQueryFacade.class.getName()).log(Level.SEVERE, null, ex);
            status = false;
        }
        return status;
    }

    //duyet tat ca follow cua question va luu notification
    public boolean sendNotificationWhenAnswer(Answer answer, int question_id) {
        //lay ho ten ng tra loi
        String fullName = answer.getAuthor().getFirstname() + " " + answer.getAuthor().getLastname();
        String sql_query = "SELECT * FROM "
                + QuestionFollow.TABLE_NAME
                + " WHERE "
                + QuestionFollow.COLUMN_QUESTION_ID
                + " = ? && "
                + QuestionFollow.COLUMN_USER_ID
                + " != ?";

        try {
            preparedStatement = DBConnection.getInstance().getConnection().prepareStatement(sql_query);
            preparedStatement.setInt(1, question_id);
            preparedStatement.setInt(2, answer.getAuthor().getIdUser());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                //lay thong tin nguoi nhan
                User recipient = UserConnection.getUserConnection().findById(resultSet.getInt(QuestionFollow.COLUMN_USER_ID));
                //tao content 
                String content = CreateContentNotification.getCreateContentService().createNotifyAddAnswerContent(fullName,
                        resultSet.getString(QuestionFollow.COLUMN_ROLE)
                );
                //lay thong tin question
                Question question = QuestionConnection.getQuestionConnection().findById(question_id).get(0);
                //tao url
                String url = CreateUrlNotification.getCreateUrlService().createNotifyAddAnswerUrl(
                        question_id,
                        question.getTitle_raw(),
                        answer.getId()
                );
                //khoi tao notifiacation
                Notification n = new Notification();
                n.setRecipient(recipient);
                n.setContent(content);
                n.setUrl(url);
                //luu notification vao db
                NotificationConnection.getNotificationConnection().addNewNotification(n);
            }

        } catch (SQLException ex) {
            Logger.getLogger(NotificationQueryFacade.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    //duyet tat ca follow cua answer va luu notification
    public boolean sendNotificationWhenComment(Comment comment, int answer_id) {
        //lay ho ten ng comment
        String fullName = comment.getAuthor().getFirstname() + " " + comment.getAuthor().getLastname();

        String sql_query = "SELECT * FROM "
                + AnswerFollow.TABLE_NAME
                + " WHERE "
                + AnswerFollow.COLUMN_ANSWER_ID
                + " = ? && "
                + AnswerFollow.COLUMN_USER_ID
                + " != ?";
        try {
            preparedStatement = DBConnection.getInstance().getConnection().prepareStatement(sql_query);
            preparedStatement.setInt(1, answer_id);
            preparedStatement.setInt(2, comment.getAuthor().getIdUser());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                //lay thong tin nguoi nhan
                User recipient = UserConnection.getUserConnection().findById(resultSet.getInt(AnswerFollow.COLUMN_USER_ID));
                //tao content 
                String content = CreateContentNotification.getCreateContentService().createNotifyAddCommentContent(fullName,
                        resultSet.getString(AnswerFollow.COLUMN_ROLE)
                );
                //lay thong tin question
                Question question = QuestionConnection.getQuestionConnection().findByCommentId(comment.getId()).get(0);
                //tao url
                String url = CreateUrlNotification.getCreateUrlService().createNotifyAddAnswerUrl(
                        question.getId(),
                        question.getTitle_raw(),
                        answer_id
                );
                //khoi tao notifiacation
                Notification n = new Notification();
                n.setRecipient(recipient);
                n.setContent(content);
                n.setUrl(url);
                //luu notification vao db
                NotificationConnection.getNotificationConnection().addNewNotification(n);
            }

        } catch (SQLException ex) {
            Logger.getLogger(NotificationQueryFacade.class.getName()).log(Level.SEVERE, null, ex);
        }

        return true;
    }
    
    public boolean sendNotificationWhenReply(Reply reply, int thread_id) {
        //lay ho ten ng comment
        String fullName = reply.getAuthor().getFirstname() + " " + reply.getAuthor().getLastname();
        
        String sql_query = "SELECT * FROM thread_follow WHERE thread_id = ? && user_id != ?";
        
        try {
            preparedStatement = DBConnection.getInstance().getConnection().prepareStatement(sql_query);
            preparedStatement.setInt(1, thread_id);
            preparedStatement.setInt(2, reply.getAuthor().getIdUser());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                //lay thong tin nguoi nhan
                User recipient = UserConnection.getUserConnection().findById(resultSet.getInt(ThreadFollow.COLUMN_USER_ID));
                
                //noi dung
                String content = "<span>" + fullName + "</span>" + " đã phản hồi bài viết mà bạn theo dõi.";
                
                //lay thong tin thread
                Threads thead = ThreadConnection.getThreadConnection().getByOnlyId(thread_id).get(0);
                //tao url
                String url = CreateUrlNotification.getCreateUrlService().createNotifyAddReplyUrl(
                        thead.getId(),
                        thead.getTitleRaw(),
                        reply.getId()
                );
                //khoi tao notifiacation
                Notification n = new Notification();
                n.setRecipient(recipient);
                n.setContent(content);
                n.setUrl(url);
                //luu notification vao db
                NotificationConnection.getNotificationConnection().addNewNotification(n);
            }
        } catch (SQLException ex) {
            Logger.getLogger(NotificationQueryFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
    
    public boolean sendNotificationWhenThread(Threads thread, int group_id) {
        //lay ho ten ng comment
        String fullName = thread.getAuthor().getFirstname() + " " + thread.getAuthor().getLastname();
        
        String sql_query = "SELECT * FROM group_follow WHERE group_id = ? && user_id != ?";
        
        try {
            preparedStatement = DBConnection.getInstance().getConnection().prepareStatement(sql_query);
            preparedStatement.setInt(1, group_id);
            preparedStatement.setInt(2, thread.getAuthor().getIdUser());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                //lay thong tin nguoi nhan
                User recipient = UserConnection.getUserConnection().findById(resultSet.getInt(ThreadFollow.COLUMN_USER_ID));
                
                //noi dung
                String content = "<span>" + fullName + "</span>" + " thêm bài viết trong nhóm mà bạn theo dõi.";
                
                //lay thong tin thread
                Threads thead = ThreadConnection.getThreadConnection().getByOnlyId(thread.getId()).get(0);
                //tao url
                String url = CreateUrlNotification.getCreateUrlService().createNotifyAddReplyUrl(
                        thead.getId(),
                        thead.getTitleRaw(),
                        0
                );
                //khoi tao notifiacation
                Notification n = new Notification();
                n.setRecipient(recipient);
                n.setContent(content);
                n.setUrl(url);
                //luu notification vao db
                NotificationConnection.getNotificationConnection().addNewNotification(n);
            }
        } catch (SQLException ex) {
            Logger.getLogger(NotificationQueryFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
}
