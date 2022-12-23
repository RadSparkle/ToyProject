package com.toyproject.api.auth.user.service;

import com.toyproject.api.auth.user.dto.AuthDto;

public interface AuthService {
    void insertUser(AuthDto.signUp user);

    AuthDto.signInInfo getUser(AuthDto.signIn user);
}
