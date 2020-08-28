/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.thread_follow;

/**
 *
 * @author nguye
 */
public interface ThreadFollowInterface {
    public boolean addFollow(int thread_id, int user_id, String role);
    public boolean checkFollowed(int thread_id, int user_id);
    public boolean deleteByThreadId(int thread_id);
    public String getListFollow(int t_id);
}
