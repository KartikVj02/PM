package com.coachbar.pms.config;


import java.util.Date;
import java.util.function.Function;

import com.coachbar.pms.service.JWTUserDetailsService;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;

import javax.crypto.SecretKey;

@Component
public class JWTUtil {



    @Value("${jwt.expiration}")
    private long jwtExpiration;
    @Autowired
    private JWTUserDetailsService userDetailsService;

    private static final SecretKey SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS512);

    public String generateToken(String username) {

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        Date now = new Date();
        Date expiration = new Date(now.getTime() + jwtExpiration);
        return Jwts.builder().setSubject(userDetails.getUsername()).setIssuedAt(now).setExpiration(expiration)
                .signWith(SECRET_KEY).compact();
        //SignatureAlgorithm.HS512, jwtSecret
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            return true;
        } catch (SignatureException e) {
            return false;
        }
    }

    public boolean isTokenExpired(String token) {
        Date expiration = extractExpiration(token);
        return expiration.before(new Date());
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
        return claimsResolver.apply(claims);
    }
}
