
package com.mncarrizo.portfolio.service;

import com.mncarrizo.portfolio.model.Person;
import com.mncarrizo.portfolio.repository.IPersonRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Marina
 */

@Service
@Transactional
public class PersonService implements IPersonService {

    @Autowired IPersonRepository personRepository;
    
    @Override
    public List<Person> getPersons() {
        List<Person> persons = personRepository.findAll();
        return persons;
    }

    @Override
    public void savePerson(Person person) {
        personRepository.save(person);
    }

    @Override
    public void deletePerson(Long id) {
        personRepository.deleteById(id);
    }

    @Override
    public Person findPerson(Long id) {
        Person person = personRepository.findById(id).orElse(null);
        return person;
    }
    
}
