package com.mackittipat.userapi.util;

import com.mackittipat.userapi.dto.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultClaims;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class JwtUtil {

    private static final String SECRET_KEY = "helloworld";

    public static String generateToken(Authentication authentication, User user) {

        List<String> authorityList = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        DefaultClaims defaultClaims = new DefaultClaims();
        defaultClaims.setSubject(user.getUsername());
        defaultClaims.setIssuedAt(Date.from(LocalDateTime.now()
                .atZone(ZoneId.systemDefault())
                .toInstant()));
        defaultClaims.setExpiration(Date.from(LocalDateTime.now()
                .plusDays(7)
                .atZone(ZoneId.systemDefault())
                .toInstant()));
        defaultClaims.put("user", user);
        defaultClaims.put("scope", authorityList);


        return Jwts.builder()
                .setClaims(defaultClaims)
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact()
        ;
    }

    public static Claims decode(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }

    public static boolean isTokenValid(String token) {
        Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token);
        return true;
    }
}
