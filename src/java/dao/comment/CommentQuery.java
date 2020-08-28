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
public class CommentQuery implements CommentQueryInterface{
    private CommentQueryFacade commentQueryFacade;
    public CommentQuery(CommentQueryFacade commentQueryFacade){
        this.commentQueryFacade = commentQueryFacade;
    }

    @Override
    public ArrayList<Comment> getByAnswerId(int id) {
        ArrayList<Object> key = new ArrayList<>();
        key.add(id);
        return this.commentQueryFacade.find(CommentQueryFacade.GET_COMMENT_BY_ANSWER_ID, key);
    }

    @Override
    public int addNewComment(Comment comment, int answer_id) {
        return this.commentQueryFacade.update(CommentQueryFacade.ADD_NEW_COMMENT, comment, answer_id);
    }

    @Override
    public boolean checkQuestionId(int comment_id) {
        return this.commentQueryFacade.checkIdUser(CommentQueryFacade.CHECK_AUTHOR_QUESTION_ID, comment_id);
    }

    @Override
    public boolean checkAnswerId(int comment_id) {
        return this.commentQueryFacade.checkIdUser(CommentQueryFacade.CHECK_AUTHOR_ANSWER_ID, comment_id);
    }

    @Override
    public boolean deleteByAnswerId(int a_id) {
        ArrayList<Object> key = new ArrayList<>();
        key.add(a_id);
        return this.commentQueryFacade.delete(CommentQueryFacade.DELETE_BY_ANSWER_ID, key);

    }

    @Override
    public boolean deleteById(int c_id) {
        ArrayList<Object> key = new ArrayList<>();
        key.add(c_id);
        return this.commentQueryFacade.delete(CommentQueryFacade.DELETE_BY_ID, key);
    }

    @Override
    public int editComment(Comment commnet) {
        return this.commentQueryFacade.update(CommentQueryFacade.EDIT_COMMENT, commnet, 0);
    }
    
}
