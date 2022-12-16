package com.toyproject.api.auth.user.controller;

import com.toyproject.api.auth.user.dto.UserDto;
import com.toyproject.api.auth.user.service.UserService;
import com.toyproject.api.auth.user.service.impl.UserServiceImpl;
import com.toyproject.api.common.DefaultResponse;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import static org.springframework.http.HttpStatus.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/user")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation("회원가입")
    @PostMapping("/sign-up")
    public ResponseEntity<Object> signUp (@RequestBody UserDto.signUp user) {
        //입력한 비밀번호 암호화
        String encryPwd = DigestUtils.sha256Hex(user.getPwd());
        user.setPwd(encryPwd);

        try {
            userService.insertUser(user);

        } catch (DuplicateKeyException e) {
            return DefaultResponse.from(BAD_REQUEST.value(),"아이디 중복", user).build();

        }
        return DefaultResponse.from(CREATED.value(),"아이디 생성 성공", user).build();
    }

    @ApiOperation("로그인")
    @GetMapping("/sign-in")
    public ResponseEntity<Object> signIn (@RequestBody UserDto.signIn user) {
        //입력한 비밀번호 암호화
        String encryPwd = DigestUtils.sha256Hex(user.getPwd());
        user.setPwd(encryPwd);

        UserDto.signIn userInfo = userService.getUser(user);
        if(userInfo == null) {
            return DefaultResponse.from(NOT_FOUND.value(), "아이디 또는 비밀번호가 맞지않습니다.", user).build();

        }
        return DefaultResponse.from(NOT_FOUND.value(), "로그인 성공", userInfo).build();
    }
}
