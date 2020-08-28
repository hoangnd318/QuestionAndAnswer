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
public class Admin extends Person{

    public static String TABLE_NAME = "admin";
    public static String COLUMN_ID_ADMIN = "id_admin";
    public static String COLUMN_ADMIN_ACCOUNT_ID = "admin_account_id";
    public static String COLUMN_PERSON_ID = "person_id";

    private int idAdmin;
    private AdminAccount account;

    public int getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(int idAdmin) {
        this.idAdmin = idAdmin;
    }

    public AdminAccount getAccount() {
        return account;
    }

    public void setAccount(AdminAccount account) {
        this.account = account;
    }

}
