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
public class DepartmentQuery implements DepartmentQueryInterface{
    private DepartmentQueryFacade departmentQueryFacade;
    
    public DepartmentQuery(DepartmentQueryFacade departmentQueryFacade){
        this.departmentQueryFacade = departmentQueryFacade;
    }
    
    @Override
    public Department findById(int id) {
        return this.departmentQueryFacade.find(DepartmentQueryFacade.FIND_BY_ID, id).get(0);
    }

    @Override
    public ArrayList<Department> findAll() {
        return departmentQueryFacade.find(DepartmentQueryFacade.FIND_ALL, 0);
    }
    
}
