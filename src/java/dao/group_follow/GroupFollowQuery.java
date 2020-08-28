/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.group_follow;

/**
 *
 * @author nguye
 */
public class GroupFollowQuery implements GroupFollowInterface {

    GroupFollowFacade gff;

    public GroupFollowQuery(GroupFollowFacade gff) {
        this.gff = gff;
    }

    @Override
    public boolean addFollow(int group_id, int user_id, String role) {
        return gff.addFollow(group_id, user_id, role);
    }

    @Override
    public boolean unFollow(int group_id, int user_id) {
        return gff.unFollow(group_id, user_id);
    }

    @Override
    public boolean checkFollow(int group_id, int user_id) {
        return gff.checkFollow(GroupFollowFacade.CHECK_FOLLOW, group_id, user_id);
    }

    @Override
    public boolean checkAdmin(int group_id, int user_id) {
        return gff.checkFollow(GroupFollowFacade.CHECK_ADMIN, group_id, user_id);
    }

    @Override
    public String getListFollow(int g_id) {
        return gff.get(g_id);
    }

}
