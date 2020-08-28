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
public interface QuestionTagInterface {
    public ArrayList<Tag> findTagByQuestionId(int id);
    public void addTagQuestion(int question_id, String tags);
    public void removeTag(int question_id);
}
