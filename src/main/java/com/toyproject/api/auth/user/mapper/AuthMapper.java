package com.toyproject.api.auth.user.mapper;

import com.toyproject.api.auth.user.dto.AuthDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AuthMapper {
    void insertUser(AuthDto.signUp user);

    AuthDto.signIn getUser(AuthDto.signIn user);
}
