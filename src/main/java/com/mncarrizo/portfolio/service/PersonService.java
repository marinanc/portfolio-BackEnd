
package com.mncarrizo.portfolio.service;

import com.mncarrizo.portfolio.model.Person;
import com.mncarrizo.portfolio.repository.IPersonRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Marina
 */

@Service
@Transactional
public class PersonService {

    @Autowired 
    IPersonRepository personRepository;
    
    public List<Person> listPersons() {
        return personRepository.findAll();
    }
    
    public Optional<Person> getOne(int id){
        return personRepository.findById(id);
    }
    
    public Optional<Person> getByName(String name){
        return personRepository.findByName(name);
    }
    
    public Optional<Person> getByLastname(String lastname){
        return personRepository.findByLastname(lastname);
    }
    
    public void save(Person p){
        personRepository.save(p);
    }
    
    public void delete(int id){
        personRepository.deleteById(id);
    }
    
    public boolean existsById(int id){
        return personRepository.existsById(id);
    }
    
    public boolean existsByName(String name){
        return personRepository.existsByName(name);
    }
    
    public boolean existsByLastname(String lastname){
        return personRepository.existsByName(lastname);
    }
    
}
