package com.toyproject.api.auth.user.mapper;

import com.toyproject.api.auth.user.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    void insertUser(UserDto.signUp user);
}
