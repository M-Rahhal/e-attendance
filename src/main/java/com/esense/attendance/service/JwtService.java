package com.esense.attendance.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;


@Service
public class JwtService {

    @Value("${app.jwt.secret}")
    private static String secretKey;

    private static final long EXPIRATION_TIME = 1000L * 60L * 60L * 24L * 7L; // 24 hours in milliseconds

    public  String generateToken(String subject , Map<String, Object> claims) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + EXPIRATION_TIME);

        return Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .addClaims(claims)
                .compact();
    }

    public  Claims parseToken(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
    }

}
