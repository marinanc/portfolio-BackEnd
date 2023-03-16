package com.mncarrizo.portfolio.security.service;

import com.mncarrizo.portfolio.security.entity.RootUser;
import com.mncarrizo.portfolio.security.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author Marina
 */

@Service
public class UserDetailsImp implements UserDetailsService {
    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getByUsername(username).get();
        return RootUser.build(user);
    }
}
