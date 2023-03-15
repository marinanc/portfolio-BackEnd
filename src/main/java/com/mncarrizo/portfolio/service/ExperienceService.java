package com.mncarrizo.portfolio.service;

import com.mncarrizo.portfolio.model.Experience;
import com.mncarrizo.portfolio.repository.IExperienceRepository;
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
public class ExperienceService {
    @Autowired
    IExperienceRepository experienceRepository;
    
    public List<Experience> listExperiences() {
        return experienceRepository.findAll();
    }
    
    public Optional<Experience> getOne(int id){
        return experienceRepository.findById(id);
    }
    
    public Optional<Experience> getByName(String name){
        return experienceRepository.findByName(name);
    }
    
    public void save(Experience exp){
        experienceRepository.save(exp);
    }
    
    public void delete(int id){
        experienceRepository.deleteById(id);
    }
    
    public boolean existsById(int id){
        return experienceRepository.existsById(id);
    }
    
    public boolean existsByName(String name){
        return experienceRepository.existsByName(name);
    }
}
