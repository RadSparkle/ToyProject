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
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
            return DefaultResponse.from(BAD_REQUEST.value(), "이메일 또는 닉네임 중복", user).build();

        }
        return DefaultResponse.from(CREATED.value(),"아이디 생성 성공", user).build();
    }

//    @ApiOperation("회원가입 메일 인증")
//    @PostMapping("/send-mail")
//    public ResponseEntity<Object> sendMail (){
//
//
//    }


    @ApiOperation("로그인")
    @PostMapping("/sign-in")
    public ResponseEntity<Object> signIn (@RequestBody UserDto.signIn user, HttpServletResponse response) {
        //입력한 비밀번호 암호화
        String encryPwd = DigestUtils.sha256Hex(user.getPwd());
        user.setPwd(encryPwd);

        UserDto.signIn userInfo = userService.getUser(user);
        if(userInfo == null) {
            return DefaultResponse.from(NOT_FOUND.value(), "아이디 또는 비밀번호가 맞지않습니다.", user).build();
        }

        Cookie idCookie = new Cookie("uid", String.valueOf(userInfo.getUid()));

        response.addCookie(idCookie);

        return DefaultResponse.from(OK.value(), "로그인 성공", userInfo).build();
    }

    @ApiOperation("로그아웃")
    @PostMapping("/sign-out")
    public ResponseEntity<Object> signOut (@CookieValue Cookie uid, HttpServletResponse response) {
        if(uid == null){
            return DefaultResponse.from(NOT_FOUND.value(), "옳바르지 않은 요청입니다.", uid).build();
        }

        expireCookie(response, uid);

        return DefaultResponse.from(OK.value(), "로그아웃 성공", uid).build();
    }

    private void expireCookie(HttpServletResponse response, Cookie uid) {
        uid.setMaxAge(0);
        response.addCookie(uid);
    }
}
