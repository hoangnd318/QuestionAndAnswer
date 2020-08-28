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
public interface ReplyQueryInterface {
    public ArrayList<Reply> getByThreadId(int thread_id);
    public ArrayList<Reply> getById(int reply_id);
    public int addReply(Reply reply, int thread_id);
    public int updateReply(Reply reply);
    public boolean deleteById(int reply_id);
    public boolean deleteByThreadId(int thread_id);
}
