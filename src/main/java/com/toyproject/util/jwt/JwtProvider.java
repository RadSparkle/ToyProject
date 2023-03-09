package com.toyproject.util.jwt;

import antlr.StringUtils;
import com.toyproject.util.StringUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.*;
import static com.toyproject.util.jwt.JwtOption.*;
import static io.jsonwebtoken.lang.Strings.UTF_8;

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

    public static boolean isBearer(String token) {
        if (StringUtil.isBlankString(token)) {
            return false;
        }

        return token.toLowerCase(Locale.ROOT)
                .startsWith(JWT_AUTH_TYPE.toLowerCase(Locale.ROOT));
    }

    public Claims claimsChk(String token) {
        String key = Base64.getEncoder().encodeToString(JWT_SECRET_KEY.getBytes(UTF_8));

        if (isBearer(token)) {
            return Jwts.parser()
                    .setSigningKey(key.getBytes())
                    .parseClaimsJws(token.substring(7))
                    .getBody();
        }

        return Jwts.parser()
                .setSigningKey(key.getBytes())
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean tokenExpired(Claims claims) {
        Date date = new Date();
        Date expDate = claims.get(EXPIRED, Date.class);    // 만료시간 값 가져오기

        long exp = expDate.getTime() / 1000; // 만료시간/1000
        expDate.setTime(exp); // 1000으로 나눈 만료시간 세팅

        /*
         * 현재 시간이 더 크면 true
         * 현재 시간이 더 작으면 false
         */
        return date.getTime() > expDate.getTime();
    }

    public String getAccessToken(Claims claims) {
        return claims.get(ACCESS_TOKEN, String.class);	// 액세스 토큰 구하기
    }

    public String getUserId(Claims claims) {
        return claims.get(USER_ID, String.class); // 사용자 아이디 구하기
    }
}
