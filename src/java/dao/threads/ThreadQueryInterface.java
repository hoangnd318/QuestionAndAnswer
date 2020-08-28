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
public interface ThreadQueryInterface {
    public ArrayList<Threads> getByGroupId(int group_id);
    public ArrayList<Threads> getById(int thread_id, String title);
    public ArrayList<Threads> getByOnlyId(int thread_id);
    public int addThread(Threads t, int group_id);
    public int updateThread(Threads t);
    public boolean deleteById(int thread_id);
}
