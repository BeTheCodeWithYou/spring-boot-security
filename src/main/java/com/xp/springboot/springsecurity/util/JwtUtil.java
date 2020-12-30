package com.xp.springboot.springsecurity.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil {

    private static final String SECRET_KEY = "jwt-signing-secret";

    public String extractUserName(String jwtToken){
        return extractClaims(jwtToken, Claims::getSubject);
    }

    public Date extractExpiration(String jwtToken) {
        return extractClaims(jwtToken, Claims::getExpiration);
    }

    public Boolean isTokenExpired(String jwtToken){
        return extractExpiration(jwtToken).before(new Date());
    }

    public Boolean validateToken(String jwtToken, UserDetails userDetails) {
        if(!isTokenExpired(jwtToken)){
            final String userName = extractUserName(jwtToken);
            return userName.equalsIgnoreCase(userDetails.getUsername());
        } else {
            return Boolean.FALSE;
        }
    }

    public <T> T extractClaims(String token, Function<Claims, T> claimResolver) {
       final Claims claims = extractAllClaims(token);
       return claimResolver.apply(claims);
    }

    private Claims extractAllClaims(String jwtToken) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(jwtToken).getBody();
    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDetails.getUsername());
    }

    private String createToken(Map<String, Object> claims, String subject){

        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+ 1000 * 60 * 60 * 8 ))
                        .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();

    }

}
