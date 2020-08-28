/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.tag;

import dao.dbconnection.DBConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Tag;

/**
 *
 * @author nguye
 */
public class TagQueryFacade {
    //tim kiem
    public static int FIND_ALL = 1;
    public static int FIND_BY_ID = 2;
    public static int FIND_BY_NAME = 3;
    public static int FIND_LIKE_NAME = 4;

    //them
    public static int ADD_NEW_TAG = 1;
    
    private PreparedStatement preparedStatement;

    public TagQueryFacade() {
        this.preparedStatement = null;
    }

    public ArrayList<Tag> find(int type, Object key) {
        ArrayList<Tag> tags = new ArrayList<>();
        String sql_query = "";
        if (type == TagQueryFacade.FIND_ALL) {
            sql_query = "SELECT * FROM " + Tag.TABLE_NAME;
        } else if (type == TagQueryFacade.FIND_BY_ID) {
            sql_query = "SELECT * FROM " + Tag.TABLE_NAME + " WHERE " + Tag.COLUMN_ID + " = ?";
        } else if (type == TagQueryFacade.FIND_BY_NAME) {
            sql_query = "SELECT * FROM " + Tag.TABLE_NAME + " WHERE " + Tag.COLUMN_NAME + " = ?";
        } else if (type == TagQueryFacade.FIND_LIKE_NAME) {
            sql_query = "SELECT * FROM " + Tag.TABLE_NAME + " WHERE " + Tag.COLUMN_NAME + " LIKE ?";
        }

        try {
            preparedStatement = DBConnection.getInstance().getConnection().prepareStatement(sql_query);
            if (type == TagQueryFacade.FIND_ALL) {
            } else if (type == TagQueryFacade.FIND_BY_ID) {
                int id = (int) key;
                preparedStatement.setInt(1, id);
            } else if (type == TagQueryFacade.FIND_BY_NAME) {
                String name = (String) key;
                preparedStatement.setString(1, name);
            } else if (type == TagQueryFacade.FIND_LIKE_NAME) {
                String name = (String) key;
                preparedStatement.setString(1, "%" + name + "%");
            }
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Tag tag = new Tag();
                tag.setId(resultSet.getInt(Tag.COLUMN_ID));
                tag.setName(resultSet.getString(Tag.COLUMN_NAME));
                tags.add(tag);
            }

        } catch (SQLException ex) {
            Logger.getLogger(TagQueryFacade.class.getName()).log(Level.SEVERE, null, ex);
        }

        return tags;
    }
    
    public int update(int type, Tag tag){
        int tag_id = 0;
        String sql_query = "";
        if(type == TagQueryFacade.ADD_NEW_TAG){
            sql_query = "INSERT INTO " 
                    + Tag.TABLE_NAME
                    + "(" + Tag.COLUMN_NAME + ") VALUES(?)";
        }
        try {
            preparedStatement = DBConnection
                    .getInstance()
                    .getConnection()
                    .prepareStatement(sql_query, Statement.RETURN_GENERATED_KEYS);
            if(type == TagQueryFacade.ADD_NEW_TAG){
                preparedStatement.setString(1, tag.getName());
            }
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if(rs.next())
            {
                tag_id = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TagQueryFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tag_id;
    }
}
