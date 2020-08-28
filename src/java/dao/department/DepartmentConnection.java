/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.department;

/**
 *
 * @author nguye
 */
public class DepartmentConnection {
    
    private static DepartmentQuery instance = null;
    
    public static DepartmentQueryInterface getDepartmentConnection(){
        if (instance == null) {
            instance = new DepartmentQuery(new DepartmentQueryFacade());
        }
        return instance;
    }
}
