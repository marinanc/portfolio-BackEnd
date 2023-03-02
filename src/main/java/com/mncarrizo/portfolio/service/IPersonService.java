
package com.mncarrizo.portfolio.service;

import com.mncarrizo.portfolio.model.Person;
import java.util.List;

/**
 *
 * @author Marina
 */
public interface IPersonService {
    
    public List<Person> getPersons();
    
    public void savePerson(Person person);
    
    public void deletePerson(Long id);
    
    public Person findPerson(Long id);
    
}
