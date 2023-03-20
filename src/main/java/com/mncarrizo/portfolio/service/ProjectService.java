package com.mncarrizo.portfolio.service;

import com.mncarrizo.portfolio.model.Project;
import com.mncarrizo.portfolio.repository.IProjectRepository;
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
public class ProjectService {
    @Autowired
    IProjectRepository projectRepository;
    
    public List<Project> listProjects() {
        return projectRepository.findAll();
    }
    
    public Optional<Project> getOne(int id) {
        return projectRepository.findById(id);
    }
    
    public Optional<Project> getByName(String name) {
        return projectRepository.findByName(name);
    }
    
    public void save(Project proj) {
        projectRepository.save(proj);
    }
    
    public void delete(int id) {
        projectRepository.deleteById(id);
    }
    
    public boolean existsById(int id) {
        return projectRepository.existsById(id);
    }
    
    public boolean existsByName(String name) {
        return projectRepository.existsByName(name);
    }
}
