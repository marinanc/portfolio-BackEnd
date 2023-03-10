package com.mncarrizo.portfolio.security.service;

import com.mncarrizo.portfolio.security.entity.Role;
import com.mncarrizo.portfolio.security.enums.RoleName;
import com.mncarrizo.portfolio.security.repository.IRoleRepository;
import jakarta.transaction.Transactional;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Marina
 */

@Service
@Transactional
public class RoleService {
    @Autowired
    IRoleRepository roleRepository;
    
    public Optional<Role> getByRoleName(RoleName roleName) {
        return roleRepository.findByRoleName(roleName);
    }
    
    public void save(Role role) {
        roleRepository.save(role);
    }
}
