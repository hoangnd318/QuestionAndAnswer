/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.comment;

import java.util.ArrayList;
import model.Comment;

/**
 *
 * @author nguye
 */
public interface CommentQueryInterface {
    public ArrayList<Comment> getByAnswerId(int id);
    public int addNewComment(Comment comment, int answer_id);
    public int editComment(Comment commnet);
    public boolean checkQuestionId(int comment_id);
    public boolean checkAnswerId(int comment_id);
    public boolean deleteByAnswerId(int a_id);
    public boolean deleteById(int c_id);
}
