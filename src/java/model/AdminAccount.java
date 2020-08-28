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
public class AdminAccount extends Account{

    public static String TABLE_NAME = "admin_account";
    public static String COLUMN_ID_ADMIN_ACCOUNT = "id_admin_account";
    public static String COLUMN_ACCOUNT_ID = "account_id";

    private int idAdminAccount;

    public int getIdAdminAccount() {
        return idAdminAccount;
    }

    public void setIdAdminAccount(int idAdminAccount) {
        this.idAdminAccount = idAdminAccount;
    }

}
