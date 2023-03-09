package com.toyproject.util.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.*;
import static com.toyproject.util.jwt.JwtOption.*;

public class JwtProvider {

    private static final String TYPE = "typ";

    private static final String ALGORITHM = "alg";

    private static final String EXPIRED = "exp";

    private static final String ACCESS_TOKEN = "access_token";

    private static final String ACCESS_TYPE = "access_tp";

    private static final String UID = "uid";

    private static final String USER_ID = "user_id";

    private static final String USER_TYPE = "user_tp";

    public String createAccessToken(JwtPayLoad payLoad, String key) {
        return createAccessToken(payLoad, key, null);
    }

    public String createAccessToken(JwtPayLoad payLoad, String key, Long addExpiredTime) {
        Map<String, Object> headers = new HashMap<>();
        headers.put(TYPE, JWT_TYP); // 타입 : JWT
        headers.put(ALGORITHM, JWT_ALG); // 알고리즘방식 : HS256

        Date date = new Date();
        long exp = date.getTime() + JWT_ACCESS_EXPIRED_TIME;

        if (addExpiredTime != null) {
            exp = date.getTime() + addExpiredTime;
        }

        payLoad.setExp(exp);

        return Jwts.builder()
                .setExpiration(new Date(exp))
                .setHeader(headers)
                .setClaims(payLoad.getMap())
                .signWith(SignatureAlgorithm.HS256, key.getBytes())
                .compact();
    }

    public String createRefreshToken(String accessToken, String key) {
        Map<String, Object> headers = new HashMap<>();
        headers.put(TYPE, JWT_TYP); // 타입 : JWT
        headers.put(ALGORITHM, JWT_ALG); // 알고리즘방식 : HS256

        Date date = new Date();
        long exp = date.getTime() + JWT_REFRESH_EXPIRED_TIME; //현재시간 + 만료시간

        Map<String, Object> payloads = new HashMap<>();

        payloads.put(ACCESS_TOKEN, accessToken);
        payloads.put(EXPIRED, exp);

        return Jwts.builder().setExpiration(new Date(exp))
                .setHeader(headers)
                .setClaims(payloads)
                .signWith(SignatureAlgorithm.HS256, key.getBytes())
                .compact();
    }
}
