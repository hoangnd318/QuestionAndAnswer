/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.account_avatar;

/**
 *
 * @author nguye
 */
public class AccountAvatarConnection {

    private static AccountAvatarQuery instance = null;

    public static AccountAvatarQueryInterface getAccountAvatarConnection() {

        if (instance == null) {
            instance = new AccountAvatarQuery(new AccountAvatarQueryFacade());
        }
        return instance;
    }
}
