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
public class UserAccount extends Account{

    public static String TABLE_NAME = "user_account";
    public static String COLUMN_ID_USER_ACCOUNT = "id_user_account";
    public static String COLUMN_ACCOUNT_ID = "account_id";

    private int idUserAccount;

    public int getIdUserAccount() {
        return idUserAccount;
    }

    public void setIdUserAccount(int idUserAccount) {
        this.idUserAccount = idUserAccount;
    }

}
