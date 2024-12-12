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
        private final long jwtExpirationInMs = 3600000; // 1 hour

        public String generateToken(String email, String role) {
            return Jwts.builder()
                    .setSubject(email)
                    .claim("role", role)
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationInMs))
                    .signWith(Keys.hmacShaKeyFor(secretBytes), SignatureAlgorithm.HS512)
                    .compact();
        }

        public Claims extractClaims(String token) {
            return Jwts.parserBuilder()
                    .setSigningKey(Keys.hmacShaKeyFor(secretBytes))
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
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
            return extractClaims(token).getSubject();
        }

        public String extractRole(String token) {
            return extractClaims(token).get("role", String.class);
        }
    }


