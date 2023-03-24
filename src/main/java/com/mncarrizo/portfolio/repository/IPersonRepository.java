package com.mncarrizo.portfolio.repository;

import com.mncarrizo.portfolio.model.Person;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Marina
 */

@Repository
public interface IPersonRepository extends JpaRepository<Person,Integer>{
    public Optional<Person> findByName(String name);
    public Optional<Person> findByLastname(String lastname);
    public boolean existsByName(String name);
    public boolean existsByLastname(String lastname);
}
