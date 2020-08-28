/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.group;

import java.util.ArrayList;
import model.Group;

/**
 *
 * @author nguye
 */
public interface GroupQueryInterface {
    public ArrayList<Group> getGroupDefault();
    public ArrayList<Group> getGroupByUserId(int user_id);
    public ArrayList<Group> getGroupAdmin(int user_id);
    public ArrayList<Group> getGroupNoneAdmin(int user_id);
    public ArrayList<Group> getGroupById(int g_id, int user_id);
    public ArrayList<Group> getGroupByName(String name, int user_id);
    public int createGroup(Group group);
    public int updateGroup(Group group);
}
