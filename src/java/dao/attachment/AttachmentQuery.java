/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.attachment;

import model.Attachment;

/**
 *
 * @author nguye
 */
public class AttachmentQuery implements AttachmentQueryInterface{
    private AttachmentQueryFacade attachmentQueryFacade;
    
    public AttachmentQuery(AttachmentQueryFacade attachmentQueryFacade){
        this.attachmentQueryFacade = attachmentQueryFacade;
    }

    @Override
    public Attachment findById(int id) {
        return this.attachmentQueryFacade.find(id);
    }
}
