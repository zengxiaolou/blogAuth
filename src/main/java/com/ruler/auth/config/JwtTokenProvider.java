package com.ruler.auth.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.ruler.auth.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Component
public class JwtTokenProvider {
    public final String secretKey;
    private final Long validityInMilliseconds;

    @Autowired
    public JwtTokenProvider(@Value("${jwt.secretKey}") String secretKey,
                            @Value("${jwt.tokenValidity}") Long validityInMilliseconds)
    {
        this.secretKey = secretKey;
        this.validityInMilliseconds = validityInMilliseconds;
    }

    public String generateToken(String username, Collection<Role> roles) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        return JWT.create().withSubject(username).withClaim("roles", roles.stream().map(Role::getName).collect(
                Collectors.toList())).withIssuedAt(new Date()).withExpiresAt(
                new Date(System.currentTimeMillis() + validityInMilliseconds)).sign(algorithm);
    }

    public boolean validateToken(String token) {
        try {
            JWT.require(Algorithm.HMAC256(secretKey)).build().verify(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
