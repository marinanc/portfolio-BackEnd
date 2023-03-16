package com.mncarrizo.portfolio.security.service;

import com.mncarrizo.portfolio.security.entity.User;
import com.mncarrizo.portfolio.security.repository.IUserRepository;
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
public class UserService {
    @Autowired
    IUserRepository userRepository;
    
    public Optional<User> getByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    
    public boolean existsByUsername(String username){
        return userRepository.existsByUsername(username);
    }
    
    public boolean existsByEmail(String emal){
        return userRepository.existsByEmail(emal);
    }
    
    public void save(User user){
        userRepository.save(user);
    }
}
