package com.mncarrizo.portfolio.security.repository;

import com.mncarrizo.portfolio.security.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Marina
 */

@Repository
public interface IUserRepository extends JpaRepository<User, Integer>{
    Optional<User> findByUserName(String userName);
    
    boolean existsByUserName(String userName);
    boolean existsByEmail(String email);
}
