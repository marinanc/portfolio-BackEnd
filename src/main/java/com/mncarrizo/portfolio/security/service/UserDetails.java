package com.mncarrizo.portfolio.security.service;

import com.mncarrizo.portfolio.security.entity.RootUser;
import com.mncarrizo.portfolio.security.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author Marina
 */

@Service
public class UserDetails implements UserDetailsService {
    @Autowired
    UserService userService;

    @Override
    public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userService.getByUserName(userName).get();
        return RootUser.build(user);
    }
}
