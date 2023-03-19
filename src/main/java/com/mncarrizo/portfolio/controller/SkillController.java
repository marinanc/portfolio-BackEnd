package com.mncarrizo.portfolio.controller;

import com.mncarrizo.portfolio.dto.dtoSkill;
import com.mncarrizo.portfolio.model.Skill;
import com.mncarrizo.portfolio.security.controller.Message;
import com.mncarrizo.portfolio.service.SkillService;
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
@RequestMapping("skill")
@CrossOrigin("http://localhost:4200")
public class SkillController {
    @Autowired
    SkillService skillService;
    
    @GetMapping("/list")
    public ResponseEntity<List<Skill>> listSkills() {
        List<Skill> skills = skillService.listSkills();
        return new ResponseEntity(skills, HttpStatus.OK);
    }
    
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody dtoSkill dtoS) {
        if(StringUtils.isBlank(dtoS.getName()))
            return new ResponseEntity(new Message("El nombre de la skill es obligatorio"), HttpStatus.BAD_REQUEST);       
        if(skillService.existsByName(dtoS.getName()))
            return new ResponseEntity(new Message("Esa skill ya exite"), HttpStatus.BAD_REQUEST);
        
        Skill skill = new Skill(dtoS.getName(), dtoS.getPercentage());
        skillService.save(skill);
        
        return new ResponseEntity(new Message("Skill agregada exitosamente"), HttpStatus.OK);
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoSkill dtoS){
        if(!skillService.existsById(id))
            return new ResponseEntity(new Message("ID inexistente"), HttpStatus.NOT_FOUND);
        
        //Si ya existe una skill con ese nombre(es decir, con otra ID)
        if(skillService.existsByName(dtoS.getName()) && 
                skillService.getByName(dtoS.getName()).get().getId() != id)
            return new ResponseEntity(new Message("Ya existe una skill con ese nombre"), HttpStatus.BAD_REQUEST);
        
        if(StringUtils.isBlank(dtoS.getName()))
            return new ResponseEntity(new Message("El nombre de la skill es obligatorio"), HttpStatus.BAD_REQUEST);
        
        Skill skill = skillService.getOne(id).get();
        skill.setName(dtoS.getName());
        skill.setPercentage(dtoS.getPercentage());
        
        skillService.save(skill);
        
        return new ResponseEntity(new Message("Skill actualizada exitosamente"), HttpStatus.OK);
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if(!skillService.existsById(id))
            return new ResponseEntity(new Message("ID inexistente"), HttpStatus.NOT_FOUND);
        
        skillService.delete(id);
        
        return new ResponseEntity(new Message("Skill eliminada exitosamente"), HttpStatus.OK);
    }
    
    @GetMapping("/detail/{id}")
    public ResponseEntity<Skill> detail(@PathVariable("id") int id){
        if(!skillService.existsById(id))
            return new ResponseEntity(new Message("ID inexistente"), HttpStatus.NOT_FOUND);
        
        Skill skill = skillService.getOne(id).get();
        
        return new ResponseEntity(skill, HttpStatus.OK);
    }
}
