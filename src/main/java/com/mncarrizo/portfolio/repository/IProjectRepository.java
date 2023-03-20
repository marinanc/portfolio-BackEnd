package com.mncarrizo.portfolio.repository;

import com.mncarrizo.portfolio.model.Project;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Marina
 */

@Repository
public interface IProjectRepository extends JpaRepository<Project, Integer>{
    public Optional<Project> findByName(String name);
    public boolean existsByName(String name);
}
