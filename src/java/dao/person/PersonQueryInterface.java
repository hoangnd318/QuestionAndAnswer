/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.person;

import model.Person;

/**
 *
 * @author nguye
 */
public interface PersonQueryInterface {
    public Person findById(int id);
    public int createNewPerson(Person p);
    public int update(Person p);
}
