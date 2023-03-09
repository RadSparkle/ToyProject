package com.toyproject.util.jwt;

import java.util.Base64;

import static java.nio.charset.StandardCharsets.UTF_8;

public class JwtOption {

    public static final String JWT_TYP = "JWT"; //JWT타입

    public static final String JWT_ALG = "HS256"; //JWT 알고리즘 방식 : HS256

    public static final String JWT_AUTH_TYPE = "Bearer";

    public static final String JWT_SECRET_KEY = "toyproject:root";

    public static final long JWT_ACCESS_EXPIRED_TIME = 1000*60*60*3L;

    public static final long JWT_REFRESH_EXPIRED_TIME = 1000*60*60*24*14L;

    private JwtOption(){}

    public static String encodeSecretKey(){
        return Base64.getEncoder().encodeToString(JWT_SECRET_KEY.getBytes(UTF_8));
    }

}
