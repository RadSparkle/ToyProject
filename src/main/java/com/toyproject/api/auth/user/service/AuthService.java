package com.toyproject.api.auth.user.service;

import com.toyproject.api.auth.user.dto.AuthDto;
import com.toyproject.api.common.model.auth.TokenVo;
import com.toyproject.util.jwt.JwtPayLoad;

public interface AuthService {
    void insertUser(AuthDto.signUp user);

    AuthDto.signIn getUser(AuthDto.signIn user);

    TokenVo getToken(JwtPayLoad build);
}
