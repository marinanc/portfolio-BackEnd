package com.mncarrizo.portfolio.repository;

import com.mncarrizo.portfolio.model.Experience;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Marina
 */

@Repository
public interface IExperienceRepository extends JpaRepository<Experience, Integer> {
    public Optional<Experience> findByName(String name);
    public boolean existsByName(String name);
}
