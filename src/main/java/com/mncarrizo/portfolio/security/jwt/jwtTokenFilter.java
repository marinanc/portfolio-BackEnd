package com.mncarrizo.portfolio.security.jwt;

import com.mncarrizo.portfolio.security.service.UserDetailsImp;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 *
 * @author Marina
 */


public class jwtTokenFilter extends OncePerRequestFilter{
    private Logger logger = LoggerFactory.getLogger(jwtTokenFilter.class);
    
    @Autowired
    jwtProvider provider;
    @Autowired
    UserDetailsImp userDetailsImp;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String token = getToken(request);
            if(token != null && provider.validateToken(token)){
                String username = provider.getUserNameFromToken(token);
                UserDetails userDetails = userDetailsImp.loadUserByUsername(username);
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }
        catch (Exception e) {
            logger.error("Fallo el método doFilterInternal");
        }
        
        filterChain.doFilter(request, response);
    }
    
    private String getToken(HttpServletRequest request){
        String header = request.getHeader("Authorization");
        if(header != null && header.startsWith("Bearer"))
            return header.replace("Bearer","");
        return null;
    }
}
