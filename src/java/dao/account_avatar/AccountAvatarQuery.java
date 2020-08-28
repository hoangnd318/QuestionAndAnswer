/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.account_avatar;

import model.AccountAvatar;

/**
 *
 * @author nguye
 */
public class AccountAvatarQuery implements AccountAvatarQueryInterface{
    private AccountAvatarQueryFacade accountAvatarQueryFacade;
    
    public AccountAvatarQuery(AccountAvatarQueryFacade accountAvatarQueryFacade){
        this.accountAvatarQueryFacade = accountAvatarQueryFacade;
    }
    
    @Override
    public AccountAvatar findById(int id) {
        return this.accountAvatarQueryFacade.find(id);
    }
    
}
