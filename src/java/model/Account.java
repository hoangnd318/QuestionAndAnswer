/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author nguye
 */
public class Account {

    public static String TABLE_NAME = "account";
    public static String COLUMN_ID = "id";
    public static String COLUMN_EMAIL = "email";
    public static String COLUMN_USERNAME = "username";
    public static String COLUMN_PASSWORD = "password";
    public static String COLUMN_ACCOUNT_AVATAR_ID = "account_avatar_id";

    private int id;
    private String email;
    private String username;
    private String password;
    private AccountAvatar avatar;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public AccountAvatar getAvatar() {
        return avatar;
    }

    public void setAvatar(AccountAvatar avatar) {
        this.avatar = avatar;
    }

}
