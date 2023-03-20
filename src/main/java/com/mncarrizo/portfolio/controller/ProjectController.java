package com.mncarrizo.portfolio.controller;

import com.mncarrizo.portfolio.dto.dtoProject;
import com.mncarrizo.portfolio.model.Project;
import com.mncarrizo.portfolio.security.controller.Message;
import com.mncarrizo.portfolio.service.ProjectService;
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

/**
 *
 * @author Marina
 */

@RequestMapping("project")
@CrossOrigin(origins = "http://localhost:4200")
public class ProjectController {
    @Autowired
    ProjectService projectService;
    
    @GetMapping("/list")
    public ResponseEntity<List<Project>> listProjects() {
        List<Project> list = projectService.listProjects();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody dtoProject dtoProj){
        if(StringUtils.isBlank(dtoProj.getName()))
            return new ResponseEntity(new Message("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(dtoProj.getDescription()))
            return new ResponseEntity(new Message("La descripción es obligatoria"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(dtoProj.getLink()))
            return new ResponseEntity(new Message("El link a evidencia es obligatorio"), HttpStatus.BAD_REQUEST);
        
        if(projectService.existsByName(dtoProj.getName()))
            return new ResponseEntity(new Message("Ya existe un proyecto con ese nombre"), HttpStatus.BAD_REQUEST);
        
        Project project = new Project(dtoProj.getName(), dtoProj.getDescription(), dtoProj.getLink());
        projectService.save(project);
        
        return new ResponseEntity(new Message("Proyecto añadido exitosamente"), HttpStatus.OK);
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoProject dtoProj){
        if(!projectService.existsById(id))
            return new ResponseEntity(new Message("ID inexistente"), HttpStatus.NOT_FOUND);
        
        //Si ya existe un proyecto con el mismo nombre(es decir, con otra id)
        if(projectService.existsByName(dtoProj.getName()) &&
                projectService.getByName(dtoProj.getName()).get().getId() != id)
            return new ResponseEntity(new Message("Ya existe un proyecto con ese nombre"), HttpStatus.BAD_REQUEST);
        
        if(StringUtils.isBlank(dtoProj.getName()))
            return new ResponseEntity(new Message("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(dtoProj.getDescription()))
            return new ResponseEntity(new Message("La descripción es obligatoria"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(dtoProj.getLink()))
            return new ResponseEntity(new Message("El link a evidencia es obligatorio"), HttpStatus.BAD_REQUEST);
        
        Project project = projectService.getOne(id).get();
        project.setName(dtoProj.getName());
        project.setDescription(dtoProj.getDescription());
        project.setLink(dtoProj.getLink());
        
        projectService.save(project);
        
        return new ResponseEntity(new Message("Proyecto actualizado exitosamente"), HttpStatus.OK);
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if(!projectService.existsById(id))
            return new ResponseEntity(new Message("ID inexistente"), HttpStatus.NOT_FOUND);
        
        projectService.delete(id);
        
        return new ResponseEntity(new Message("Proyecto eliminado exitosamente"), HttpStatus.OK);
    }
    
    @GetMapping("/detail/{id}")
    public ResponseEntity<Project> getById(@PathVariable("id") int id) {
        if(!projectService.existsById(id))
            return new ResponseEntity(new Message("ID inexistente"), HttpStatus.NOT_FOUND);
        
        Project project = projectService.getOne(id).get();
        
        return new ResponseEntity(project, HttpStatus.OK);
    }
}
