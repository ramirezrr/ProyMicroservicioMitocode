package com.mitocode.microservices.authentication_server_jwt.config.security;

import com.mitocode.microservices.authentication_server_jwt.model.entity.UserEntity;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {

    @Value("${mitocode.security.key:mitocode}")
    private String key;

    public String generateToken(UserDetails userDetails) {

        UserEntity userEntity = (UserEntity) userDetails;

        Map<String, Object> claims = new HashMap<>();
        claims.put("email", userEntity.getEmail());
        claims.put("lastname", userEntity.getLastname());
        claims.put("roles", userEntity.getRoles());

        return Jwts.builder()
                .setSubject(userEntity.getUsername())
                .setIssuedAt(new Date())
                .signWith(getSignKey())
                .setExpiration(new Date(System.currentTimeMillis() + (60 * 1000)))
                .addClaims(claims)
                .compact();
    }

    private Key getSignKey() {
        return Keys.hmacShaKeyFor(Base64.getEncoder().encode(key.getBytes()));
    }

}
