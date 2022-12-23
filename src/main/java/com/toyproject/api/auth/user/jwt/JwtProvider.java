package com.toyproject.api.auth.user.jwt;


import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.util.Base64;
import java.util.Date;

@Component
@RestController
public class JwtProvider {

    @Value("${jwt.password}")
    private String secretKey;

    public String createToken(String userId) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + Duration.ofDays(1).toMillis());

        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setIssuer("TEST")
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS256, secretKey.getBytes())
                .compact();
    }

    public Claims parseJwtToken(String token) {
        token = BearerRemove(token);

        return Jwts.parser()
                .setSigningKey(Base64.getEncoder().encodeToString(secretKey.getBytes()))
                .parseClaimsJws(token)
                .getBody();
    }

    private String BearerRemove(String token) {
        return token.substring("Bearer ".length());
    }
}
