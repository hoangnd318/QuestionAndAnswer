/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.reply;

import java.util.ArrayList;
import model.Reply;

/**
 *
 * @author nguye
 */
public class ReplyQuery implements ReplyQueryInterface{
    
    ReplyQueryFacade rqf;
    
    public ReplyQuery(ReplyQueryFacade rqf){
        this.rqf = rqf;
    }

    @Override
    public ArrayList<Reply> getByThreadId(int thread_id) {
        ArrayList<Object> key = new ArrayList<>();
        key.add(thread_id);
        return rqf.find(ReplyQueryFacade.GET_BY_THREAD_ID, key);
    }

    @Override
    public int addReply(Reply reply, int thread_id) {
        return rqf.update(ReplyQueryFacade.ADD_NEW, reply, thread_id);
    }

    @Override
    public ArrayList<Reply> getById(int reply_id) {
        ArrayList<Object> key = new ArrayList<>();
        key.add(reply_id);
        return rqf.find(ReplyQueryFacade.GET_BY_ID, key);
    }

    @Override
    public int updateReply(Reply reply) {
        return rqf.update(ReplyQueryFacade.EDIT_REPLY, reply, 0);
    }

    @Override
    public boolean deleteById(int reply_id) {
        ArrayList<Object> key = new ArrayList<>();
        key.add(reply_id);
        return rqf.delete(ReplyQueryFacade.DELETE_BY_ID, key);
    }

    @Override
    public boolean deleteByThreadId(int thread_id) {
        ArrayList<Object> key = new ArrayList<>();
        key.add(thread_id);
        return rqf.delete(ReplyQueryFacade.DELETE_BY_THREAD_ID, key);
    }
    
}
