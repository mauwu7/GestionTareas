package org.example.backend.security;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.stereotype.Component;


import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
public class JwtService {
    private static final String SECRET_KEY = "5367566859703373367639792F423F452848284D6251655468576D5A71347437";

    private Key getSigningKey(){
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(UserDetails userDetails){
        return Jwts.builder()
                .subject(userDetails.getUsername()) //El email es el subject
                .claim("roles", userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() +1000*60*60*10)) //10 horas
                .signWith(getSigningKey()).compact();
    }

    public String extraerEmail(String token){
        return extraerClaims(token).getSubject();
    }

    public boolean esTokenValido(String token, UserDetails userDetails){
        String email = extraerEmail(token);
        return email.equals(userDetails.getUsername()) && !esTokenExpirado(token);
    }

    private boolean esTokenExpirado(String token){
        return extraerClaims(token).getExpiration().before(new Date());
    }

    private Claims extraerClaims(String token){
        return Jwts.parser()
                .verifyWith((SecretKey) getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
