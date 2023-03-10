package com.mncarrizo.portfolio.security.repository;

import com.mncarrizo.portfolio.security.entity.Role;
import com.mncarrizo.portfolio.security.enums.RoleName;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Marina
 */

@Repository
public interface IRoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByRoleName(RoleName roleName);
}
