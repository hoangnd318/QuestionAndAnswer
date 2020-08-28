/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.user;

import java.util.ArrayList;
import model.User;

/**
 *
 * @author nguye
 */
public class UserQuery implements UserQueryInterface{
    private UserQueryFacade userQueryFacade;
    
    public UserQuery(UserQueryFacade userQueryFacade){
        this.userQueryFacade = userQueryFacade;
    }
    
    @Override
    public User findById(int id) {
        ArrayList<Object> key = new ArrayList<>();
        key.add(id);
        return new UserQueryFacade().find(UserQueryFacade.FIND_BY_ID, key).get(0);
    }

    @Override
    public User findByUserAccountId(int id) {
        ArrayList<Object> key = new ArrayList<>();
        key.add(id);
        return new UserQueryFacade().find(UserQueryFacade.FIND_BY_USER_ACCOUNT_ID, key).get(0);
    }

    @Override
    public int updatePoint(User user) {
        return this.userQueryFacade.update(UserQueryFacade.UPDATE_POINT, user);
    }

    @Override
    public int createNewUser(User user) {
        return this.userQueryFacade.update(UserQueryFacade.CREATE_NEW, user);
    }

    @Override
    public int updateProfileSV(User user) {
        return userQueryFacade.update(UserQueryFacade.UPDATE_PROFILE_SV, user);
    }
    
}
