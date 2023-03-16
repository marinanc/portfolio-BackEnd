package com.mncarrizo.portfolio.security.jwt;

import com.mncarrizo.portfolio.security.entity.RootUser;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

/**
 *
 * @author Marina
 */

@Component
public class jwtProvider {
    private Logger logger = LoggerFactory.getLogger(jwtProvider.class);
    
    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration}")
    private int expiration;
    
    public String generateToken(Authentication authentication){
        RootUser rootUser = (RootUser) authentication.getPrincipal();
        return Jwts.builder().setSubject(rootUser.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + expiration*1000))
                .signWith(SignatureAlgorithm.ES512, secret)
                .compact();
    }
    
    public String getUsernameFromToken(String token){
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
    }
    
    public boolean validateToken(String token){
        try{
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        }
        catch(MalformedJwtException e){
            logger.error("Token mal formado");
        }
        catch(UnsupportedJwtException e){
            logger.error("Token no soportado");
        }
        catch(ExpiredJwtException e){
            logger.error("Token expirado");
        }
        catch(IllegalArgumentException e){
            logger.error("Token vacio");
        }
        catch(SignatureException e){
            logger.error("Firma no valida");
        }
        return false;
    }
}
