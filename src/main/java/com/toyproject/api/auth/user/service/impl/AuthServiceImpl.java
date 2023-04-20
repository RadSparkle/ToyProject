package com.toyproject.api.auth.user.service.impl;

import com.toyproject.api.auth.user.dto.AuthDto;
import com.toyproject.api.auth.user.mapper.AuthMapper;
import com.toyproject.api.auth.user.service.AuthService;
import com.toyproject.api.common.model.auth.TokenVo;
import com.toyproject.util.jwt.JwtOption;
import com.toyproject.util.jwt.JwtPayLoad;
import com.toyproject.util.jwt.JwtProvider;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.toyproject.util.jwt.JwtOption.JWT_AUTH_TYPE;


@Service("AuthService")
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthMapper authMapper;

    @Override
    public void userAdd(AuthDto.signUp user) {
       authMapper.insertUser(user);
    }

    @Override
    public AuthDto.signIn userDetails(AuthDto.signIn user) {
        return authMapper.selectUser(user);
    }

    @Override
    public TokenVo getToken(JwtPayLoad payLoad) {
        String key = JwtOption.encodeSecretKey();
        JwtProvider provider = new JwtProvider();
        String accessToken = provider.createAccessToken(payLoad, JwtOption.encodeSecretKey());
        String refreshToken = provider.createRefreshToken(accessToken, JwtOption.encodeSecretKey());

        Claims accessTokenClaims = provider.claimsChk(JWT_AUTH_TYPE + " " + accessToken);
        Claims refreshTokenClaims = provider.claimsChk(JWT_AUTH_TYPE + " " +  refreshToken);

        long accessTokenExp = provider.getExpSec(accessTokenClaims);
        long refreshTokenExp = provider.getExpSec(refreshTokenClaims);

        TokenVo vo = new TokenVo();

        vo.setAccess_token(accessToken);
        vo.setAccess_token_expires_in((int) accessTokenExp);
        vo.setRefresh_token(refreshToken);
        vo.setRefresh_token_expires_in((int) refreshTokenExp);

        return vo;
    }
}
