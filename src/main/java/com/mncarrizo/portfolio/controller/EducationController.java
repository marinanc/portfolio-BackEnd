package com.mncarrizo.portfolio.controller;

import com.mncarrizo.portfolio.dto.dtoEducation;
import com.mncarrizo.portfolio.model.Education;
import com.mncarrizo.portfolio.security.controller.Message;
import com.mncarrizo.portfolio.service.EducationService;
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
@RequestMapping("education")
@CrossOrigin(origins = {"https://frontend-portfolio-mnc.web.app", "http://localhost:4200"})
public class EducationController {
    @Autowired
    EducationService educationService;
    
    @GetMapping("/list")
    public ResponseEntity<List<Education>> listEducation(){
        List<Education> list = educationService.listEducation();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody dtoEducation dtoEdu){
        if(StringUtils.isBlank(dtoEdu.getTitle()))
            return new ResponseEntity(new Message("El titulo es obligatorio"), HttpStatus.BAD_REQUEST);
        
        if(educationService.existsByTitle(dtoEdu.getTitle()))
            return new ResponseEntity(new Message("El titulo ya existe"), HttpStatus.BAD_REQUEST);
        
        Education education = new Education(dtoEdu.getTitle(), dtoEdu.getDescription());
        educationService.save(education);
        
        return new ResponseEntity(new Message("Educacion agregada exitosamente"), HttpStatus.OK);
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoEducation dtoEdu){
        if(!educationService.existsById(id))
            return new ResponseEntity(new Message("El ID no existe"), HttpStatus.NOT_FOUND);
        
        //Si ya existe el titulo en otra educacion (es decir, con otra ID)
        if(educationService.existsByTitle(dtoEdu.getTitle()) &&
                educationService.getByTitle(dtoEdu.getTitle()).get().getId() != id)
            return new ResponseEntity(new Message("El titulo ya existe"), HttpStatus.BAD_REQUEST);
        
        if(StringUtils.isBlank(dtoEdu.getTitle()))
            return new ResponseEntity(new Message("El titulo es obligatorio"), HttpStatus.BAD_REQUEST);
        
        Education education = educationService.getOne(id).get();
        education.setTitle(dtoEdu.getTitle());
        education.setDescription(dtoEdu.getDescription());
        
        educationService.save(education);
        
        return new ResponseEntity(new Message("Educacion actualizada exitosamente"), HttpStatus.OK);
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id){
        if(!educationService.existsById(id))
            return new ResponseEntity(new Message("El ID no existe"), HttpStatus.NOT_FOUND);
        
        educationService.delete(id);
        
        return new ResponseEntity(new Message("Educacion eliminada exitosamente"), HttpStatus.OK);
    }
    
    @GetMapping("/detail/{id}")
    public ResponseEntity<Education> getById(@PathVariable("id") int id){
        if(!educationService.existsById(id))
            return new ResponseEntity(new Message("El ID no existe"), HttpStatus.NOT_FOUND);
        
        Education education = educationService.getOne(id).get();
        
        return new ResponseEntity(education, HttpStatus.OK);
    }
}
