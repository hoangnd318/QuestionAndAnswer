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
public class AccountAvatar extends Attachment {

    public static String TABLE_NAME = "account_avatar";
    public static String COLUMN_ID_ACCOUNT_AVATAR = "id_account_avatar";
    public static String COLUMN_ATTACHMENT_ID = "attachment_id";
    public static String NO_AVATAR_URL = "/view/frontend/web/images/user/avatar/no-avatar.jpg";
    
    private int idAccountAvatar;

    public int getIdAccountAvatar() {
        return idAccountAvatar;
    }

    public void setIdAccountAvatar(int idAccountAvatar) {
        this.idAccountAvatar = idAccountAvatar;
    }
}
