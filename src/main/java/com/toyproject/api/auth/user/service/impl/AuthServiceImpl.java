package com.toyproject.api.auth.user.service.impl;

import com.toyproject.api.auth.user.dto.AuthDto;
import com.toyproject.api.auth.user.jwt.JwtOption;
import com.toyproject.api.auth.user.jwt.JwtPayLoad;
import com.toyproject.api.auth.user.jwt.JwtProvider;
import com.toyproject.api.auth.user.mapper.AuthMapper;
import com.toyproject.api.auth.user.service.AuthService;
import com.toyproject.api.common.model.auth.TokenVo;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.toyproject.api.auth.user.jwt.JwtOption.JWT_AUTH_TYPE;


@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthMapper authMapper;

    @Override
    public void insertUser(AuthDto.signUp user) {
       authMapper.insertUser(user);
    }

    @Override
    public AuthDto.signIn getUser(AuthDto.signIn user) {
        return authMapper.getUser(user);
    }

    @Override
    public AuthDto.signIn getUserInfo(int uid) {
        return authMapper.getUserInfo(uid);
    }

    @Override
    public void getToken(JwtPayLoad payLoad) throws Exception {
//        String key = JwtOption.encodeSecretKey();
//        JwtProvider provider = new JwtProvider();
//        String accessToken = provider.createAccessToken(payLoad, JwtOption.encodeSecretKey());
//        String refreshToken = provider.createRefreshToken(accessToken, JwtOption.encodeSecretKey());
//
//        Claims accessTokenClaims = provider.claimsChk(JWT_AUTH_TYPE + " " + accessToken);
//        Claims refreshTokenClaims = provider.claimsChk(JWT_AUTH_TYPE + " " +  refreshToken);
//
//        long accessTokenExp = provider.getExpSec(accessTokenClaims);
//        long refreshTokenExp = provider.getExpSec(refreshTokenClaims);
//
//        TokenVo vo = new TokenVo();
//
//        vo.setAccess_token(accessToken);
//        vo.setAccess_token_expires_in((int) accessTokenExp);
//        vo.setRefresh_token(refreshToken);
//        vo.setRefresh_token_expires_in((int) refreshTokenExp);
//
//        return vo;
    }
}
