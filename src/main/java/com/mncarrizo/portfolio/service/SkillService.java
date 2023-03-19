package com.mncarrizo.portfolio.service;

import com.mncarrizo.portfolio.model.Skill;
import com.mncarrizo.portfolio.repository.ISkillRepository;
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
public class SkillService {
    @Autowired
    ISkillRepository skillRepository;
    
    public List<Skill> listSkills() {
        return skillRepository.findAll();
    }
    
    public Optional<Skill> getOne(int id) {
        return skillRepository.findById(id);
    }
    
    public Optional<Skill> getByName(String name) {
        return skillRepository.findByName(name);
    }
    
    public void save(Skill skill) {
        skillRepository.save(skill);
    }
    
    public void delete(int id) {
        skillRepository.deleteById(id);
    }
    
    public boolean existsByName(String name) {
        return skillRepository.existsByName(name);
    }
    
    public boolean existsById(int id) {
        return skillRepository.existsById(id);
    }
}
