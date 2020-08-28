/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Timestamp;

/**
 *
 * @author nguye
 */
public class Person {

    public static String TABLE_NAME = "person";
    public static String COLUMN_ID = "id";
    public static String COLUMN_FIRSTNAME = "firstname";
    public static String COLUMN_LASTNAME = "lastname";
    public static String COLUMN_MIDNAME = "midname";
    public static String COLUMN_ADDRESS = "address";
    public static String COLUMN_GENDER = "gender";
    public static String COLUMN_BIRTHDAY = "birthday";

    private int id;
    private String firstname;
    private String lastname;
    private String midname;
    private String address;
    private String gender;
    private Timestamp birthday;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getMidname() {
        return midname;
    }

    public void setMidname(String midname) {
        this.midname = midname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Timestamp getBirthday() {
        return birthday;
    }

    public void setBirthday(Timestamp birthday) {
        this.birthday = birthday;
    }

}
