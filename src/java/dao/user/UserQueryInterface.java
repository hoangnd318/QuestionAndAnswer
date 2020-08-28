/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.user;

import model.User;

/**
 *
 * @author nguye
 */
public interface UserQueryInterface {
    public User findById(int id);
    public User findByUserAccountId(int id);
    public int updatePoint(User user);
    public int createNewUser(User user);
    public int updateProfileSV(User user);
}
