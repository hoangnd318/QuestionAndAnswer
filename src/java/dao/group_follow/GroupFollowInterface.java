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
public interface GroupFollowInterface {
    public boolean addFollow(int group_id, int user_id, String role);
    public boolean unFollow(int group_id, int user_id);
    public boolean checkFollow(int group_id, int user_id);
    public boolean checkAdmin(int group_id, int user_id);
    public String getListFollow(int g_id);
}
