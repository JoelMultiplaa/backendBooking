package com.example.backendbooking2.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Date;

@Component
public class JWTutil {
    private final String jwtSecret = "Qa1WYnOn/T8Qrp6c/bBCtPlEvBOwUCf97IOzTPKblZ1PbEfHUjwJWwYqc4fw0fZ/cGpPFC/+HrbsEJhTUnQWww==";
    private final byte[] secretBytes = Base64.getDecoder().decode(jwtSecret);
    private final long jwtExpirationInMs = 15 * 60 * 1000; // 15 minutes

    public String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationInMs))
                .signWith(Keys.hmacShaKeyFor(secretBytes), SignatureAlgorithm.HS512)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(Keys.hmacShaKeyFor(secretBytes))
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }

    public String extractUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(secretBytes))
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
