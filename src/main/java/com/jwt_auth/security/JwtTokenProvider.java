package com.jwt_auth.security;

import com.jwt_auth.entity.Role;
import com.jwt_auth.entity.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Map;

@Component
public class JwtTokenProvider {

    @Value("${jwt.secret-key}")
    private String mySecretKey;

    @Value("${jwt.expiration}")
    private Duration expiration;

    private SecretKey getJwtSecretKey() {
        return Keys.hmacShaKeyFor(mySecretKey.getBytes(StandardCharsets.UTF_8));
    }

    public String generateAccessToken(User user) {
        Date issuedAt = new Date();
        Date expiresAt = new Date(issuedAt.getTime() + expiration.toMillis());

        Map<String , Object> jwtClaims = Map.of(
                "userId", user.getId(),
                "role", user.getRoles().stream()
                        .map(Role::getName)
                        .collect(java.util.stream.Collectors.toCollection(LinkedHashSet::new))
        );

        return Jwts.builder()
                .subject(user.getUsername())
                .claims(jwtClaims)
                .issuedAt(issuedAt)
                .expiration(expiresAt)
                .signWith(getJwtSecretKey())
                .compact();
    }
}
