/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.question_tag;

import java.util.ArrayList;
import model.Tag;

/**
 *
 * @author nguye
 */
public class QuestionTagQuery implements QuestionTagInterface{
    QuestionTagFacade questionTagFacade;
    
    public QuestionTagQuery(QuestionTagFacade questionTagFacade){
        this.questionTagFacade = questionTagFacade;
    }
    
    @Override
    public ArrayList<Tag> findTagByQuestionId(int id) {
        ArrayList<Object> key = new ArrayList<>();
        key.add(id);
        return this.questionTagFacade.find(QuestionTagFacade.FIND_TAG_BY_QUESION_ID, key);
    }

    @Override
    public void addTagQuestion(int question_id, String tags) {
        this.questionTagFacade.addTagQuestion(question_id, tags);
    }

    @Override
    public void removeTag(int question_id) {
        this.questionTagFacade.removeTag(question_id);
    }
    
}
