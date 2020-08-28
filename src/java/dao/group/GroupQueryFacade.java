/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.group;

import dao.dbconnection.DBConnection;
import dao.group_follow.GroupFollowConnection;
import dao.threads.ThreadConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Group;

/**
 *
 * @author nguye
 */
public class GroupQueryFacade {

    public static int GET_GROUP_DEFAULT = 1;
    public static int GET_GROUP_BY_USER_ID = 2;
    public static int GET_GROUP_BY_GROUP_ID = 3;
    public static int GET_GROUP_BY_NAME = 4;
    public static int GET_GROUP_NONE_ADMIN = 5;
    public static int GET_GROUP_ADMIN = 6;

    public static int CREATE_NEW = 1;
    public static int UPDATE = 2;

    private PreparedStatement preparedStatement;

    public GroupQueryFacade() {
        this.preparedStatement = null;
    }

    public ArrayList<Group> find(int type, ArrayList<Object> key, int user_id) {
        ArrayList<Group> groups = new ArrayList<>();
        String sql_query = "";
        if (type == GroupQueryFacade.GET_GROUP_DEFAULT) {
            sql_query = "SELECT * FROM groups WHERE groupDefault=1";
        } else if (type == GroupQueryFacade.GET_GROUP_BY_USER_ID) {
            sql_query = "SELECT b.id,b.groupCode,b.groupDefault,b.name,b.description "
                    + "FROM group_follow a "
                    + "INNER JOIN groups b "
                    + "ON b.id=a.group_id "
                    + "WHERE a.user_id=?";
        } else if (type == GroupQueryFacade.GET_GROUP_BY_GROUP_ID) {
            sql_query = "SELECT * FROM groups WHERE id = ?";
        } else if (type == GroupQueryFacade.GET_GROUP_BY_NAME) {
            sql_query = "SELECT *, MATCH (name) AGAINST (?) as score FROM groups WHERE MATCH (name) AGAINST (?) > 0 ORDER BY score DESC";
        } else if (type == GroupQueryFacade.GET_GROUP_NONE_ADMIN) {
            sql_query = "SELECT b.id,b.groupCode,b.groupDefault,b.name,b.description FROM group_follow a INNER JOIN groups b ON b.id=a.group_id WHERE a.user_id = ? && a.role != ?";
        } else if (type == GroupQueryFacade.GET_GROUP_ADMIN) {
            sql_query = "SELECT b.id,b.groupCode,b.groupDefault,b.name,b.description FROM group_follow a INNER JOIN groups b ON b.id=a.group_id WHERE a.user_id = ? && a.role = ?";
        }
        try {
            preparedStatement = DBConnection.getInstance().getConnection().prepareStatement(sql_query);
            if (type == GroupQueryFacade.GET_GROUP_BY_USER_ID) {
                preparedStatement.setInt(1, (int) key.get(0));
            } else if (type == GroupQueryFacade.GET_GROUP_BY_GROUP_ID) {
                preparedStatement.setInt(1, (int) key.get(0));
            } else if (type == GroupQueryFacade.GET_GROUP_BY_NAME) {
                preparedStatement.setString(1, (String) key.get(0));
                preparedStatement.setString(2, (String) key.get(0));
            } else if (type == GroupQueryFacade.GET_GROUP_NONE_ADMIN || type == GroupQueryFacade.GET_GROUP_ADMIN) {
                preparedStatement.setInt(1, (int) key.get(0));
                preparedStatement.setString(2, "admin");
            }
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Group group = new Group();
                group.setId(resultSet.getInt(Group.COLUMN_ID));
                group.setGroupCode(resultSet.getString(Group.COLUMN_GROUP_CODE));
                group.setGroupDefault(resultSet.getInt(Group.COLUMN_GROUP_DEFAULT));
                group.setName(resultSet.getString(Group.COLUMN_NAME));
                group.setDescription(resultSet.getString(Group.COLUMN_DESCRIPTION));
                group.setThreads(ThreadConnection.getThreadConnection().getByGroupId(resultSet.getInt(Group.COLUMN_ID)));
                group.setFollowedByUser(GroupFollowConnection.getAnswerConnection().checkFollow(resultSet.getInt(Group.COLUMN_ID), user_id));

                groups.add(group);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GroupQueryFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return groups;
    }

    public int update(int type, Group group) {
        int group_id = 0;

        String sql_query = "";
        if (type == GroupQueryFacade.CREATE_NEW) {
            sql_query = "INSERT INTO groups(name,description,groupDefault) VALUES(?,?,?)";
        } else if (type == GroupQueryFacade.UPDATE) {
            sql_query = "UPDATE groups SET name = ?, description = ? WHERE id = ?";
        }
        try {
            preparedStatement = DBConnection.getInstance().getConnection().prepareStatement(sql_query, Statement.RETURN_GENERATED_KEYS);
            if (type == GroupQueryFacade.CREATE_NEW) {
                preparedStatement.setString(1, group.getName());
                preparedStatement.setString(2, group.getDescription());
                preparedStatement.setInt(3, 0);
                preparedStatement.executeUpdate();
                ResultSet rs = preparedStatement.getGeneratedKeys();
                if (rs.next()) {
                    group_id = rs.getInt(1);
                    group.setId(group_id);
                }
                //
            } else if (type == GroupQueryFacade.UPDATE) {
                preparedStatement.setString(1, group.getName());
                preparedStatement.setString(2, group.getDescription());
                preparedStatement.setInt(3, group.getId());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException ex) {
            Logger.getLogger(GroupQueryFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return group_id;
    }
}
