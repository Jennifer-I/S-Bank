package com.jennifer.sbank.security;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.Map;

@Component
@Slf4j
public class JwtService {

    @Value("${jwt.secret}")
    private String jwtSecret;

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSecret);
        return null;
    }
    public String generateToken(Authentication authentication){
        String email = authentication.getName();
        String fullName = ((AppUserDetails) authentication.getPrincipal()).getFullName();
        Date currentTime = new Date(System.currentTimeMillis());
        Date expiryDate = new Date(currentTime.getTime()+ 60 * 100);

        return Jwts.builder()
                .setSubject(email)
                .claim("fullName", fullName)
                .claim("email", email)
                .claim("authorities", authentication.getAuthorities())
                .setIssuedAt(currentTime)
                .setExpiration(expiryDate)
                .signWith(getSigningKey())
                .compact();

    }
    public String getUserNameFromToken(String token) {
        log.info("JwtService is called to extract the users Email from the JWT");
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public Boolean validateToken(String token){
        log.info("JwtService is called to validate the JWT");
        try {
            Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token);
            return true;
        }catch (Exception e){
          log.error("Error validating JWT: {}", e.getMessage());
          return false;
      }
    }
    public Claims getClaims(String token){
        log.info("JwtService is called to get the claims from the JWT");
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    public Map<String ,String> validateTokenAndReturnDetails(String token){
        log.info("JwtService is called to validate the JWT and return the details");
        Claims claims = getClaims(token);
        return Map.of(
                "email", claims.getSubject(),
                "fullName", claims.get("fullName", String.class),
                "authorities", claims.get("authorities", String.class)
        );
    }



}
