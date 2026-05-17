package lk.tech.moonbffuser.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lk.tech.moonbffuser.dto.security.AuthUser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

@Component
public class JwtUtils {

    private final SecretKey key;

    public JwtUtils(@Value("${app.jwt.secret}") String secret) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    public Claims validateAccessToken(String token) {
        try {
            Claims claims = Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();

            String tokenType = claims.get("tokenType", String.class);

            if (!"access".equals(tokenType)) {
                return null;
            }

            return claims;
        } catch (JwtException | IllegalArgumentException e) {
            return null;
        }
    }

    public AuthUser toAuthUser(Claims claims) {
        return new AuthUser(
                UUID.fromString(claims.get("authId", String.class)),
                claims.getSubject(),
                claims.get("role", String.class),
                claims.get("dbId", Long.class)
        );
    }
}