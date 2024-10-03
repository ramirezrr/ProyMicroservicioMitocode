package com.mitocode.microservices.cloud_gateway.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import reactor.core.publisher.Mono;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
public class AuthenticationManager implements ReactiveAuthenticationManager {

    @Value("${mitocode.security.key:mitocode}")
    private String securityKey;

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        return Mono.just(authentication.getCredentials().toString())
                .map(token -> {
                    SecretKey key = Keys.hmacShaKeyFor(Base64.getEncoder().encode(securityKey.getBytes()));
                    return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
                })
                .map(claims -> {
                    String username = claims.getSubject();
                    List<String> roles = claims.get("roles", List.class);
                    Collection<GrantedAuthority> authorities = roles.stream().map(role -> new SimpleGrantedAuthority(role))
                            .collect(Collectors.toList());
                    return new UsernamePasswordAuthenticationToken(username, null, authorities);
                });
    }
}
