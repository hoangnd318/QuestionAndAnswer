/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nguye
 */
public class DBConnection {

    public static String DB_DATABASE_NAME = "ho_tro_day_va_hoc_db";
    public static String DB_URL = "jdbc:mysql://localhost:3306/" + DBConnection.DB_DATABASE_NAME + "?characterEncoding=UTF-8&useSSL=false";
    public static String DB_CLASS = "com.mysql.jdbc.Driver";
    public static String DB_USERNAME = "root";
    public static String DB_PASSWORD = "coi123457";

    private Connection dbConnection = null;
    private static DBConnection instance = null;

    private DBConnection() {
        try {
            Class.forName(DBConnection.DB_CLASS);
            dbConnection = DriverManager.getConnection(DBConnection.DB_URL, DBConnection.DB_USERNAME, DBConnection.DB_PASSWORD);
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static DBConnection getInstance() {
        try {
            if (instance == null || instance.getConnection().isClosed()) {
                instance = new DBConnection();
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return instance;
    }

    public Connection getConnection() {
        return dbConnection;
    }
}
