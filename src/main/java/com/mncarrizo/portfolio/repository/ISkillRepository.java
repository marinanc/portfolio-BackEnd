package com.mncarrizo.portfolio.repository;

import com.mncarrizo.portfolio.model.Skill;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Marina
 */

@Repository
public interface ISkillRepository extends JpaRepository<Skill, Integer> {
    public Optional<Skill> findByName(String name);
    public boolean existsByName(String name);
}
