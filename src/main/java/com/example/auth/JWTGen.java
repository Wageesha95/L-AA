package com.example.auth;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
public class JWTGen {

    @Value("${wageesha.app.jwtSecret}")
    private String jwtSecret;

    @Value("${wageesha.app.jwtExpirationMs}")
    private int jwtExpirationMin;

    public String jwtGenerate() {
        Instant now = Instant.now();

        String jwt = Jwts.builder()
                .setIssuer("Thilina Liyanage")
                .setSubject("Test1")
                .setAudience("you")
                .claim("age", "21")
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plus(jwtExpirationMin, ChronoUnit.MINUTES)))
                .signWith(SignatureAlgorithm.HS256,jwtSecret)
                .compact();

        System.out.println(jwt);
        return  jwt;
    }

    public boolean validateJwtToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch{

        }
        return false;
    }
}