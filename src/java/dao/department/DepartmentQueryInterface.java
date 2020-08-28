/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.department;

import java.util.ArrayList;
import model.Department;

/**
 *
 * @author nguye
 */
public interface DepartmentQueryInterface {
    public Department findById(int id);
    public ArrayList<Department> findAll();
}
