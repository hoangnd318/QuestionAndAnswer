/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.question_tag;

import dao.dbconnection.DBConnection;
import dao.tag.TagConnection;
import dao.tag_statistical.TagStatisticalConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Tag;

/**
 *
 * @author nguye
 */
public class QuestionTagFacade {

    public static int FIND_TAG_BY_QUESION_ID = 1;
    
    private PreparedStatement preparedStatement;

    public QuestionTagFacade() {
        this.preparedStatement = null;
    }

    public ArrayList<Tag> find(int type, ArrayList<Object> key) {
        ArrayList<Tag> tags = new ArrayList<>();
        String sql_query = "";
        if (type == QuestionTagFacade.FIND_TAG_BY_QUESION_ID) {
            sql_query = "SELECT * FROM question_tag WHERE question_id = ?";
        }
        try {
            preparedStatement = DBConnection.getInstance().getConnection().prepareStatement(sql_query);
            if (type == QuestionTagFacade.FIND_TAG_BY_QUESION_ID) {
                int id = (int) key.get(0);
                preparedStatement.setInt(1, id);
            }
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                tags.add(TagConnection.getTagConnection().findById(resultSet.getInt("tag_id")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuestionTagFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tags;
    }

    public void addTagQuestion(int question_id, String tags) {
        String sql_query = "INSERT INTO question_tag(question_id,tag_id) VALUES(?,?)";
        ArrayList<Tag> arrTags = new ArrayList<>();
        String[] tagsSplit = tags.split(",");
        for (String tagSplit : tagsSplit) {
            if (tagSplit.compareTo("") != 0) {
                int tag_id = 0;
                if (TagConnection.getTagConnection().findByName(tagSplit).isEmpty()) {
                    Tag tag = new Tag();
                    tag.setName(tagSplit);
                    tag_id = TagConnection.getTagConnection().addNewTag(tag);
                } else {
                    tag_id = TagConnection.getTagConnection().findByName(tagSplit).get(0).getId();
                }
                try {
                    TagStatisticalConnection.getTagStatisticalConnection().addNewStatistical(tag_id);
                    preparedStatement = DBConnection
                            .getInstance()
                            .getConnection()
                            .prepareStatement(sql_query);
                    preparedStatement.setInt(1, question_id);
                    preparedStatement.setInt(2, tag_id);
                    preparedStatement.executeUpdate();
                } catch (SQLException ex) {
                    Logger.getLogger(QuestionTagFacade.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public void removeTag(int question_id) {
        String sql_query = "DELETE FROM question_tag WHERE question_id=?";
        try {
            preparedStatement = DBConnection
                    .getInstance()
                    .getConnection()
                    .prepareStatement(sql_query);
            preparedStatement.setInt(1, question_id);
            preparedStatement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(QuestionTagFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
}
