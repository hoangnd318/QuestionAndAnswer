/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.notification;

/**
 *
 * @author nguye
 */
public class NotificationConnection {
    
    private static NotificationQuery instance = null;
    
    public static NotificationQueryInterface getNotificationConnection(){
        if (instance == null) {
            instance = new NotificationQuery(new NotificationQueryFacade());
        }
        return instance;
    }
}
