package com.toyproject.api.auth.user.service;

import com.toyproject.api.auth.user.dto.UserDto;

public interface UserService {
    void insertUser(UserDto.signUp user);
}
