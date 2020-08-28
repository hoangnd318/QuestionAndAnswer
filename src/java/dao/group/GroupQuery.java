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
public class GroupQuery implements GroupQueryInterface {

    GroupQueryFacade gqf;

    public GroupQuery(GroupQueryFacade gqf) {
        this.gqf = gqf;
    }

    @Override
    public ArrayList<Group> getGroupDefault() {
        return gqf.find(GroupQueryFacade.GET_GROUP_DEFAULT, null, 0);
    }

    @Override
    public ArrayList<Group> getGroupByUserId(int user_id) {
        ArrayList<Object> key = new ArrayList<>();
        key.add(user_id);
        return gqf.find(GroupQueryFacade.GET_GROUP_BY_USER_ID, key, user_id);
    }

    @Override
    public ArrayList<Group> getGroupById(int g_id, int user_id) {
        ArrayList<Object> key = new ArrayList<>();
        key.add(g_id);
        return gqf.find(GroupQueryFacade.GET_GROUP_BY_GROUP_ID, key, user_id);
    }

    @Override
    public ArrayList<Group> getGroupByName(String name, int user_id) {
        ArrayList<Object> key = new ArrayList<>();
        key.add(name);
        return gqf.find(GroupQueryFacade.GET_GROUP_BY_NAME, key, user_id);
    }

    @Override
    public int createGroup(Group group) {
        return gqf.update(GroupQueryFacade.CREATE_NEW, group);
    }

    @Override
    public int updateGroup(Group group) {
        return gqf.update(GroupQueryFacade.UPDATE, group);
    }

    @Override
    public ArrayList<Group> getGroupAdmin(int user_id) {
        ArrayList<Object> key = new ArrayList<>();
        key.add(user_id);
        return gqf.find(GroupQueryFacade.GET_GROUP_ADMIN, key, user_id);
    }

    @Override
    public ArrayList<Group> getGroupNoneAdmin(int user_id) {
        ArrayList<Object> key = new ArrayList<>();
        key.add(user_id);
        return gqf.find(GroupQueryFacade.GET_GROUP_NONE_ADMIN, key, user_id);
    }

}
