package com.mncarrizo.portfolio.controller;

import com.mncarrizo.portfolio.dto.dtoExperience;
import com.mncarrizo.portfolio.model.Experience;
import com.mncarrizo.portfolio.security.controller.Message;
import com.mncarrizo.portfolio.service.ExperienceService;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Marina
 */

@RestController
@RequestMapping("/experience")
@CrossOrigin(origins = {"https://frontend-portfolio-mnc.web.app", "http://localhost:4200"})
public class ExperienceController {
    @Autowired
    ExperienceService experienceService;
    
    @GetMapping("/list")
    public ResponseEntity<List<Experience>> listExperiences(){
        List<Experience> list = experienceService.listExperiences();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody dtoExperience dtoExp){
        if(StringUtils.isBlank(dtoExp.getName()))
            return new ResponseEntity(new Message("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if(experienceService.existsByName(dtoExp.getName()))
            return new ResponseEntity(new Message("Esa experiencia ya existe"), HttpStatus.BAD_REQUEST);
        
        Experience experience = new Experience(dtoExp.getName(), dtoExp.getDescription());
        experienceService.save(experience);
        
        return new ResponseEntity(new Message("Experiencia agregada exitosamente"), HttpStatus.OK);
    }
    
    @PutMapping("update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoExperience dtoExp){
        if(!experienceService.existsById(id))
            return new ResponseEntity(new Message("El id no existe"), HttpStatus.BAD_REQUEST);
        
        if(experienceService.existsByName(
                dtoExp.getName()) && 
                experienceService.getByName(dtoExp.getName()).get().getId() != id)
            return new ResponseEntity(new Message("Esa nombre ya existe"), HttpStatus.BAD_REQUEST);
        
        if(StringUtils.isBlank(dtoExp.getName()))
            return new ResponseEntity(new Message("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        
        Experience experience = experienceService.getOne(id).get();
        experience.setName(dtoExp.getName());
        experience.setDescription(dtoExp.getDescription());
        
        experienceService.save(experience);
        
        return new ResponseEntity(new Message("Experiencia actualizada exitosamente"), HttpStatus.OK);
    }
    
    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id){
        if(!experienceService.existsById(id))
            return new ResponseEntity(new Message("El ID no existe"), HttpStatus.BAD_REQUEST);
        
        experienceService.delete(id);
        
        return new ResponseEntity(new Message("Experiencia eliminada exitosamente"),HttpStatus.OK);
    }
    
    @GetMapping("/detail/{id}")
    public ResponseEntity<Experience> getById(@PathVariable("id") int id){
        if(!experienceService.existsById(id))
            return new ResponseEntity(new Message("El ID no existe"), HttpStatus.NOT_FOUND);
        Experience experiencie = experienceService.getOne(id).get();
        return new ResponseEntity(experiencie, HttpStatus.OK);
    }
}
