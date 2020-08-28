/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.attachment;

/**
 *
 * @author nguye
 */
public class AttachmentConnection {
    
    private static AttachmentQuery instance = null;
    
    public static AttachmentQueryInterface getAttachmentConnection(){
        if (instance == null) {
            instance = new AttachmentQuery(new AttachmentQueryFacade());
        }
        return instance;
    }
}
