/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.person;

import dao.dbconnection.DBConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Person;

/**
 *
 * @author nguye
 */
public class PersonQueryFacade {

    public static int FIND_BY_ID = 1;

    public static int CREATE_NEW = 1;
    public static int UPDATE = 2;

    private PreparedStatement preparedStatement;

    public PersonQueryFacade() {
        this.preparedStatement = null;
    }

    public ArrayList<Person> find(int type, Object key) {
        ArrayList<Person> persons = new ArrayList<>();
        String sql_query = "";
        if (type == PersonQueryFacade.FIND_BY_ID) {
            sql_query = "SELECT * FROM " + Person.TABLE_NAME + " WHERE " + Person.COLUMN_ID + "=?";
        }

        try {
            preparedStatement = DBConnection.getInstance().getConnection().prepareStatement(sql_query);
            if (type == PersonQueryFacade.FIND_BY_ID) {
                int id = (int) key;
                preparedStatement.setInt(1, id);
            }
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Person person = new Person();
                person.setId(resultSet.getInt(Person.COLUMN_ID));
                person.setFirstname(resultSet.getString(Person.COLUMN_FIRSTNAME));
                person.setMidname(resultSet.getString(Person.COLUMN_MIDNAME));
                person.setLastname(resultSet.getString(Person.COLUMN_LASTNAME));
                person.setAddress(resultSet.getString(Person.COLUMN_ADDRESS));
                person.setGender(resultSet.getString(Person.COLUMN_GENDER));
                person.setBirthday(resultSet.getTimestamp(Person.COLUMN_BIRTHDAY));
                persons.add(person);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PersonQueryFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return persons;
    }

    public int update(int type, Person p) {
        int person_id = 0;
        String sql_query = "";
        if (type == PersonQueryFacade.CREATE_NEW) {
            sql_query = "INSERT INTO "
                    + Person.TABLE_NAME
                    + "(" + Person.COLUMN_FIRSTNAME + ","
                    + Person.COLUMN_LASTNAME + ","
                    + Person.COLUMN_MIDNAME + ","
                    + Person.COLUMN_ADDRESS + ","
                    + Person.COLUMN_GENDER + ","
                    + Person.COLUMN_BIRTHDAY + ") VALUES(?,?,?,?,?,?)";
        } else if (type == PersonQueryFacade.UPDATE) {
            sql_query = "UPDATE person SET firstname=?,lastname=?,midname=?,address=?,gender=?,birthday=? WHERE id=?";
        }
        try {
            preparedStatement = DBConnection.getInstance().getConnection().prepareStatement(sql_query, Statement.RETURN_GENERATED_KEYS);
            if (type == PersonQueryFacade.CREATE_NEW) {
                preparedStatement.setString(1, p.getFirstname());
                preparedStatement.setString(2, p.getLastname());
                preparedStatement.setString(3, p.getMidname());
                preparedStatement.setString(4, p.getAddress());
                preparedStatement.setString(5, p.getGender());
                preparedStatement.setTimestamp(6, p.getBirthday());
                preparedStatement.executeUpdate();
                ResultSet rs = preparedStatement.getGeneratedKeys();
                if (rs.next()) {
                    person_id = rs.getInt(1);
                    p.setId(person_id);
                }
            } else if (type == PersonQueryFacade.UPDATE) {
                preparedStatement.setString(1, p.getFirstname());
                preparedStatement.setString(2, p.getLastname());
                preparedStatement.setString(3, p.getMidname());
                preparedStatement.setString(4, p.getAddress());
                preparedStatement.setString(5, p.getGender());
                preparedStatement.setTimestamp(6, p.getBirthday());
                preparedStatement.setInt(7, p.getId());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException ex) {
            Logger.getLogger(PersonQueryFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return person_id;
    }
}
