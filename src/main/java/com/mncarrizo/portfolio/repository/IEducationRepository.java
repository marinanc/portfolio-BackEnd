package com.mncarrizo.portfolio.repository;

import com.mncarrizo.portfolio.model.Education;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Marina
 */

@Repository
public interface IEducationRepository extends JpaRepository<Education, Integer>{
    public Optional<Education> findByTitle(String title);
    public boolean existsByTitle(String title);
}
