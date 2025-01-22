package com.mycompany.ticketmaster.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;

import java.security.Key;
import java.util.function.Function;

public class JwtService {
    @Value("${application.security.jwt.secret-key}")
    private String secret;
    @Value("${application.security.jwt.ttl}")
    private long ttl;

    public String extractUserName(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(getSigningKey()).build().parseClaimsJws(token).getBody();
    }

    private Key getSigningKey() {
        byte[] ketBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(ketBytes);
    }
}
