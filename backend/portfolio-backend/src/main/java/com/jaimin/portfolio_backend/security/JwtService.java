package com.jaimin.portfolio_backend.security;
 
import java.nio.charset.StandardCharsets;
import java.util.Date;
 
import javax.crypto.SecretKey;
 
import org.springframework.stereotype.Service;
 
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
 
@Service
public class JwtService {
 
    private static final String SECRET =
            "mySuperSecretKeyForPortfolioProject123456789";
 
    public String generateToken(String email) {
 
        return Jwts.builder()
                .subject(email)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 86400000))
                .signWith(getSignInKey())
                .compact();
    }
 
    public String extractUsername(String token) {
 
        return Jwts.parser()
                .verifyWith(getSignInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }
 
    public boolean isTokenValid(String token, String email) {
 
        String username = extractUsername(token);
 
        return username.equals(email)
                && !isTokenExpired(token);
    }
 
    private boolean isTokenExpired(String token) {
 
        Date expiration = Jwts.parser()
                .verifyWith(getSignInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getExpiration();
 
        return expiration.before(new Date());
    }
 
    private SecretKey getSignInKey() {
 
        return Keys.hmacShaKeyFor(
                SECRET.getBytes(StandardCharsets.UTF_8));
    }
}
 