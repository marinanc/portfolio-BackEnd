package com.mncarrizo.portfolio.controller;

import com.mncarrizo.portfolio.dto.dtoPerson;
import com.mncarrizo.portfolio.model.Person;
import com.mncarrizo.portfolio.security.controller.Message;
import com.mncarrizo.portfolio.service.PersonService;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Marina
 */

@RestController
@RequestMapping("/person")
@CrossOrigin(origins = {"https://frontend-portfolio-mnc.web.app", "http://localhost:4200"})
public class PersonController {
    @Autowired
    PersonService personService;
    
    @GetMapping("/list")
    public ResponseEntity<List<Person>> listPersons(){
        List<Person> list = personService.listPersons();
        return new ResponseEntity(list, HttpStatus.OK);
    }
/*    
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody dtoPerson dtoPe){
        if(StringUtils.isBlank(dtoPe.getName()))
            return new ResponseEntity(new Message("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(dtoPe.getLastname()))
            return new ResponseEntity(new Message("El apellido es obligatorio"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(dtoPe.getDescription()))
            return new ResponseEntity(new Message("La descripción es obligatoria"), HttpStatus.BAD_REQUEST);
        
        Person person = new Person(dtoPe.getName(), dtoPe.getLastname(), dtoPe.getDescription(), dtoPe.getImg());
        personService.save(person);
        
        return new ResponseEntity(new Message("Persona añadida exitosamente"), HttpStatus.OK);
    }
*/
    
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoPerson dtoPe){
        if(!personService.existsById(id))
            return new ResponseEntity(new Message("ID inexistente"), HttpStatus.NOT_FOUND);
        if(StringUtils.isBlank(dtoPe.getName()))
            return new ResponseEntity(new Message("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(dtoPe.getLastname()))
            return new ResponseEntity(new Message("El apellido es obligatorio"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(dtoPe.getDescription()))
            return new ResponseEntity(new Message("La descripción es obligatoria"), HttpStatus.BAD_REQUEST);
        
        Person person = personService.getOne(id).get();
        person.setName(dtoPe.getName());
        person.setLastname(dtoPe.getLastname());
        person.setDescription(dtoPe.getDescription());
        person.setImg(dtoPe.getImg());
        
        personService.save(person);
        
        return new ResponseEntity(new Message("Persona actualizada exitosamente"), HttpStatus.OK);
    }
    
/*
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id){
        if(!personService.existsById(id))
            return new ResponseEntity(new Message("ID inexistente"), HttpStatus.NOT_FOUND);
        
        personService.delete(id);
        
        return new ResponseEntity(new Message("Persona eliiminada exitosamente"), HttpStatus.OK);
    }
*/
    
    @GetMapping("/detail/{id}")
    public ResponseEntity<Person> getById(@PathVariable("id") int id){
        if(!personService.existsById(id))
            return new ResponseEntity(new Message("ID inexistente"), HttpStatus.NOT_FOUND);
        
        Person person = personService.getOne(id).get();
        
        return new ResponseEntity(person, HttpStatus.OK);
    }
}
