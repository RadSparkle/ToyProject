package com.toyproject.api.auth.user.service;

import com.toyproject.api.auth.user.dto.UserDto;

import java.util.List;

public interface UserService {
    void insertUser(UserDto.signUp user);
}
