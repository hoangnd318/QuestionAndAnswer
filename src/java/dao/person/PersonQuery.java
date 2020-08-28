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
public class PersonQuery implements PersonQueryInterface{
    private PersonQueryFacade personQueryFacade;
    
    public PersonQuery(PersonQueryFacade personQueryFacade){
        this.personQueryFacade = personQueryFacade;
    }
    
    @Override
    public Person findById(int id) {
        return this.personQueryFacade.find(PersonQueryFacade.FIND_BY_ID, id).get(0);
    }

    @Override
    public int createNewPerson(Person p) {
        return this.personQueryFacade.update(PersonQueryFacade.CREATE_NEW, p);
    }

    @Override
    public int update(Person p) {
        return personQueryFacade.update(PersonQueryFacade.UPDATE, p);
    }
    
}
