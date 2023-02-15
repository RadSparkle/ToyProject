package com.toyproject.api.auth.user.service;

import com.toyproject.api.auth.user.dto.AuthDto;
import com.toyproject.api.auth.user.jwt.JwtPayLoad;
import com.toyproject.api.common.model.auth.TokenVo;

public interface AuthService {
    void insertUser(AuthDto.signUp user);

    AuthDto.signInInfo getUser(AuthDto.signIn user);

    AuthDto.signIn getUserInfo(int uid);

    TokenVo getToken(JwtPayLoad payLoad) throws Exception;
}
