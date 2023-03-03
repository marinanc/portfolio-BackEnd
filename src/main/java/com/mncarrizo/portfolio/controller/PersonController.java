package com.mncarrizo.portfolio.controller;

import com.mncarrizo.portfolio.model.Person;
import com.mncarrizo.portfolio.service.IPersonService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Marina
 */

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class PersonController {
    @Autowired IPersonService personService;
    
    @GetMapping("/persons/get")
    public List<Person> getPersons(){
        return personService.getPersons();
    }
    
    @PostMapping("/persons/create")
    public String createPerson(@RequestBody Person person){
        personService.savePerson(person);
        return "La persona fue creada correctamente";
    }
    
    @DeleteMapping("/persons/delete/{id}")
    public String deletePerson(@PathVariable Long id){
        personService.deletePerson(id);
        return "La persona fue eliminada correctamente";
    }
    
    @PutMapping("/persons/edit/{id}")
    public Person editPerson(@PathVariable Long id,
                             @RequestParam("name") String newName,
                             @RequestParam("lastname") String newLastName,
                             @RequestParam("img") String newImg){
        
        Person person = personService.findPerson(id);
        person.setName(newName);
        person.setLastname(newLastName);
        person.setImg(newImg);
        
        personService.savePerson(person);
        
        return person;
    }
    
    @GetMapping("/persons/get/profile")
    public Person findPerson(){
        return personService.findPerson((long)1);
    }
}
