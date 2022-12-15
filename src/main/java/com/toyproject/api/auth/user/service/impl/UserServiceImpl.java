package com.toyproject.api.auth.user.service.impl;

import com.toyproject.api.auth.user.dto.UserDto;
import com.toyproject.api.auth.user.mapper.UserMapper;
import com.toyproject.api.auth.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void insertUser(UserDto.signUp user) {

       userMapper.insertUser(user);
    }

    @Override
    public UserDto.signIn getUser(UserDto.signIn user) {
        return userMapper.getUser(user);
    }
}
