package com.PiperChat.User.security;

import com.PiperChat.User.user.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtGenerator {

    private UserRepository userRepository;

    @Autowired
    public JwtGenerator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String generateToken(Authentication authentication){
        String username = authentication.getName();
        Long userId = userRepository.findUserIdByUsername(username);
        String role = userRepository.findUserRoleByUsername(username);
        Date currentDate = new Date();
        Date expiredDate = new Date(currentDate.getTime() + SecurityConstants.JWT_EXPIRATION);


        return Jwts.builder()
                .setSubject(username)
                .claim("userId", userId)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(expiredDate)
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }
    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SecurityConstants.JWT_SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }
    public String getUsernameFromJwt(String token){
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(SecurityConstants.JWT_SECRET)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }
    public boolean validateToken(String token){
        try{
            Jwts.parserBuilder().setSigningKey(SecurityConstants.JWT_SECRET).build().parseClaimsJws(token);
            return true;
        }catch (Exception e){
            throw new AuthenticationCredentialsNotFoundException("JWT was expired or incorrect");
        }
    }
}
