package com.mncarrizo.portfolio.service;

import com.mncarrizo.portfolio.model.Education;
import com.mncarrizo.portfolio.repository.IEducationRepository;
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
public class EducationService {
    @Autowired
    IEducationRepository educationRepository;
    
    public List<Education> listEducation(){
        return educationRepository.findAll();
    }
    
    public Optional<Education> getOne(int id){
        return educationRepository.findById(id);
    }
    
    public Optional<Education> getByTitle(String title){
        return educationRepository.findByTitle(title);
    }
    
    public void save(Education edu){
        educationRepository.save(edu);
    }
    
    public void delete(int id){
        educationRepository.deleteById(id);
    }
    
    public boolean existsById(int id){
        return educationRepository.existsById(id);
    }
    
    public boolean existsByTitle(String title){
        return educationRepository.existsByTitle(title);
    }
}
