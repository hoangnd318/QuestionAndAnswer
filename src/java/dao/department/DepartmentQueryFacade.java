/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.department;

import dao.dbconnection.DBConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Department;

/**
 *
 * @author nguye
 */
public class DepartmentQueryFacade {

    public static int FIND_BY_ID = 1;
    public static int FIND_ALL = 2;

    private PreparedStatement preparedStatement;

    public DepartmentQueryFacade() {
        this.preparedStatement = null;
    }

    public ArrayList<Department> find(int type, Object key) {
        ArrayList<Department> departments = new ArrayList<>();
        String sql_query = "";
        if (type == DepartmentQueryFacade.FIND_BY_ID) {
            sql_query = "SELECT * FROM " + Department.TABLE_NAME + " WHERE " + Department.COLUMN_ID + "=?";
        } else if (type == DepartmentQueryFacade.FIND_ALL) {
            sql_query = "SELECT * FROM department";
        }
        try {
            preparedStatement = DBConnection.getInstance().getConnection().prepareStatement(sql_query);
            if (type == DepartmentQueryFacade.FIND_BY_ID) {
                int id = (int) key;
                preparedStatement.setInt(1, id);
            }
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Department department = new Department();
                department.setId(resultSet.getInt(Department.COLUMN_ID));
                department.setName(resultSet.getString(Department.COLUMN_NAME));
                departments.add(department);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DepartmentQueryFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return departments;
    }
}
