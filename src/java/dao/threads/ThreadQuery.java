/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.threads;

import java.util.ArrayList;
import model.Threads;

/**
 *
 * @author nguye
 */
public class ThreadQuery implements ThreadQueryInterface{

    ThreadQueryFacade tqf;
    
    public ThreadQuery(ThreadQueryFacade tqf){
        this.tqf = tqf;
    }
    
    @Override
    public ArrayList<Threads> getByGroupId(int group_id) {
        ArrayList<Object> key = new ArrayList<>();
        key.add(group_id);
        return tqf.find(ThreadQueryFacade.GET_BY_GROUP_ID, key);
    }

    @Override
    public ArrayList<Threads> getById(int thread_id, String title) {
        ArrayList<Object> key = new ArrayList<>();
        key.add(thread_id);
        key.add(title);
        return tqf.find(ThreadQueryFacade.GET_BY_THREAD_ID, key);
    }

    @Override
    public int addThread(Threads t, int group_id) {
        return tqf.update(ThreadQueryFacade.ADD_NEW, t, group_id);
    }

    @Override
    public ArrayList<Threads> getByOnlyId(int thread_id) {
        ArrayList<Object> key = new ArrayList<>();
        key.add(thread_id);
        return tqf.find(ThreadQueryFacade.GET_BY_ONLY_ID, key);
    }

    @Override
    public int updateThread(Threads t) {
        return tqf.update(ThreadQueryFacade.EDIT_THREAD, t, 0);
    }

    @Override
    public boolean deleteById(int thread_id) {
        return tqf.delete(thread_id);
    }
    
}
