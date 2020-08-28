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
public class ThreadFollowQuery implements ThreadFollowInterface {

    ThreadFollowFacade tff;

    public ThreadFollowQuery(ThreadFollowFacade tff) {
        this.tff = tff;
    }

    @Override
    public boolean addFollow(int thread_id, int user_id, String role) {
        return tff.update(ThreadFollowFacade.ADD_NEW_FOLLOW, thread_id, user_id, role);
    }

    @Override
    public boolean checkFollowed(int thread_id, int user_id) {
        return tff.checkFollowed(thread_id, user_id);
    }

    @Override
    public boolean deleteByThreadId(int thread_id) {
        return tff.delete(thread_id);
    }

    @Override
    public String getListFollow(int t_id) {
        return tff.get(t_id);
    }

}
