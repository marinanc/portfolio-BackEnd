package com.mncarrizo.portfolio.security.controller;

import com.mncarrizo.portfolio.security.dto.LoginUser;
import com.mncarrizo.portfolio.security.dto.NewUser;
import com.mncarrizo.portfolio.security.dto.jwtDTO;
import com.mncarrizo.portfolio.security.entity.Role;
import com.mncarrizo.portfolio.security.entity.User;
import com.mncarrizo.portfolio.security.enums.RoleName;
import com.mncarrizo.portfolio.security.jwt.jwtProvider;
import com.mncarrizo.portfolio.security.service.RoleService;
import com.mncarrizo.portfolio.security.service.UserService;
import jakarta.validation.Valid;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Marina
 */

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = {"https://frontend-portfolio-mnc.web.app", "http://localhost:4200"})
public class AuthController {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;
    @Autowired
    jwtProvider provider;
    
    @PostMapping("/new")
    public ResponseEntity<?> newAuth(@Valid @RequestBody NewUser newUser, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity(new Message("Campos mal puestos o email invalido"), HttpStatus.BAD_REQUEST);
        
        if(userService.existsByUsername(newUser.getUsername()))
            return new ResponseEntity(new Message("Ese nombre de usuario ya existe"), HttpStatus.BAD_REQUEST);
    
        if(userService.existsByEmail(newUser.getEmail()))
            return new ResponseEntity(new Message("Ese email ya existe"), HttpStatus.BAD_REQUEST);
        
        User user = new User(
                newUser.getName(), 
                newUser.getUsername(), 
                newUser.getEmail(), 
                passwordEncoder.encode(newUser.getPassword()));
        
        Set<Role> roles = new HashSet<>();
        roles.add(roleService.getByRoleName(RoleName.ROLE_USER).get());
        
        if(newUser.getRoles().contains("admin"))
            roles.add(roleService.getByRoleName(RoleName.ROLE_ADMIN).get());
        
        user.setRoles(roles);
        userService.save(user);
        
        return new ResponseEntity(new Message("Usuario guardado exitosamente"), HttpStatus.CREATED);
    }
    
    @PostMapping("/login")
    public ResponseEntity<jwtDTO> login(@Valid @RequestBody LoginUser loginUser, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity(new Message("Campos mal puestos"), HttpStatus.BAD_REQUEST);
        
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUser.getUsername(), 
                        loginUser.getPassword()
                    )
                );
        
        SecurityContextHolder.getContext().setAuthentication(authentication);
        
        String jwt = provider.generateToken(authentication);
        
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        
        jwtDTO dto = new jwtDTO(
                jwt, 
                userDetails.getUsername(),
                userDetails.getAuthorities()
                );
        
        return new ResponseEntity(dto, HttpStatus.OK);
    }
}
