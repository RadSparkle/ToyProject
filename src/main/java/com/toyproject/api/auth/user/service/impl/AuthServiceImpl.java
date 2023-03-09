package com.toyproject.api.auth.user.service.impl;

import com.toyproject.api.auth.user.dto.AuthDto;
import com.toyproject.api.auth.user.mapper.AuthMapper;
import com.toyproject.api.auth.user.service.AuthService;
import com.toyproject.api.common.model.auth.TokenVo;
import com.toyproject.util.jwt.JwtOption;
import com.toyproject.util.jwt.JwtPayLoad;
import com.toyproject.util.jwt.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


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
    public TokenVo getToken(JwtPayLoad build) {
        String key = JwtOption.encodeSecretKey();
        JwtProvider provider = new JwtProvider();

        return null;
    }
}
