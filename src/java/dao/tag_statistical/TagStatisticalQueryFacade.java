/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.tag_statistical;

import dao.dbconnection.DBConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Tag;
import model.TagStatistical;
import service.ConvertTimestamp;

/**
 *
 * @author nguye
 */
public class TagStatisticalQueryFacade {

    public static int ADD_STATISTICAL = 1;

    public static int FIND_TREND = 1;
    public static int FIND_ALL_SORT_POPULAR = 2;
    public static int FIND_ALL_SORT_NAME = 3;
    public static int FIND_ALL_SORT_CREATE_TIME = 4;

    private PreparedStatement preparedStatement;

    public TagStatisticalQueryFacade() {
        this.preparedStatement = null;
    }

    public ArrayList<Tag> find(int type, ArrayList<Object> key) {
        ArrayList<Tag> tags = new ArrayList<>();
        String sql_query = "";
        if (type == TagStatisticalQueryFacade.FIND_TREND) {
            sql_query = "SELECT b.id,b.name,b.create_time,COUNT(*) total "
                    + "FROM tag_statistical a "
                    + "INNER JOIN tag b "
                    + "ON b.id=a.tag_id "
                    + "WHERE a.search_time >= now() - interval 7 day "
                    + "GROUP BY a.tag_id "
                    + "LIMIT 0,10";
        } else if (type == TagStatisticalQueryFacade.FIND_ALL_SORT_POPULAR) {
            sql_query = "SELECT a.id,a.name,a.create_time,COUNT(b.id) total "
                    + "FROM tag a "
                    + "LEFT JOIN tag_statistical b "
                    + "ON a.id=b.tag_id "
                    + "GROUP BY a.id "
                    + "ORDER BY total DESC "
                    + "LIMIT ?,?";
        } else if (type == TagStatisticalQueryFacade.FIND_ALL_SORT_NAME) {
            sql_query = "SELECT a.id,a.name,a.create_time,COUNT(b.id) total "
                    + "FROM tag a "
                    + "LEFT JOIN tag_statistical b "
                    + "ON a.id=b.tag_id "
                    + "GROUP BY a.name "
                    + "ORDER BY a.name ASC "
                    + "LIMIT ?,?";
        } else if (type == TagStatisticalQueryFacade.FIND_ALL_SORT_CREATE_TIME) {
            sql_query = "SELECT * "
                    + "FROM tag a "
                    + "ORDER BY a.create_time DESC "
                    + "LIMIT ?,?";
        }

        try {
            preparedStatement = DBConnection.getInstance().getConnection().prepareStatement(sql_query);
            if (type == TagStatisticalQueryFacade.FIND_ALL_SORT_CREATE_TIME
                    || type == TagStatisticalQueryFacade.FIND_ALL_SORT_NAME
                    || type == TagStatisticalQueryFacade.FIND_ALL_SORT_POPULAR) {
                int page = (int) key.get(0);
                int endPage = 21 * page;
                int startPage = endPage - 21;
                preparedStatement.setInt(1, startPage);
                preparedStatement.setInt(2, endPage);
                if (page <= 0) {
                    return tags;
                }
            }
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Tag tag = new Tag();
                tag.setId(resultSet.getInt(Tag.COLUMN_ID));
                tag.setName(resultSet.getString(Tag.COLUMN_NAME));
                tag.setCreateTime(resultSet.getTimestamp(Tag.COLUMN_CREATE_TIME));
                if (type == TagStatisticalQueryFacade.FIND_ALL_SORT_POPULAR
                        || type == TagStatisticalQueryFacade.FIND_ALL_SORT_NAME) {
                    tag.setTotal(resultSet.getInt("total"));
                }
                if (type == TagStatisticalQueryFacade.FIND_ALL_SORT_CREATE_TIME) {
                    //convert time
                    ConvertTimestamp ct = new ConvertTimestamp();
                    String timeConvert = ct.convertTimeNotify(resultSet.getTimestamp(Tag.COLUMN_CREATE_TIME));
                    tag.setConvertCreateTime(timeConvert);
                }
                tags.add(tag);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TagStatisticalQueryFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tags;
    }

    public int update(int type, int tag_id_s) {
        int tag_id = 0;
        String sql_query = "";
        if (type == TagStatisticalQueryFacade.ADD_STATISTICAL) {
            sql_query = "INSERT INTO "
                    + TagStatistical.TABLE_NAME
                    + "(" + TagStatistical.COLUMN_TAG_ID + ") VALUES(?)";
        }
        try {
            preparedStatement = DBConnection
                    .getInstance()
                    .getConnection()
                    .prepareStatement(sql_query, Statement.RETURN_GENERATED_KEYS);
            if (type == TagStatisticalQueryFacade.ADD_STATISTICAL) {
                preparedStatement.setInt(1, tag_id_s);
                preparedStatement.executeUpdate();
                ResultSet rs = preparedStatement.getGeneratedKeys();
                if (rs.next()) {
                    tag_id = rs.getInt(1);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(TagStatisticalQueryFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tag_id;
    }

    public int countTag() {
        int count = 0;
        String sql_query = "SELECT * FROM " + Tag.TABLE_NAME;
        try {
            preparedStatement = DBConnection.getInstance().getConnection().prepareStatement(sql_query);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.last()) {
                count = resultSet.getRow();
            }
        } catch (SQLException ex) {
            Logger.getLogger(TagStatisticalQueryFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }
}
