package com.toyproject.api.auth.user.controller;

import com.toyproject.api.auth.user.dto.AuthDto;
import com.toyproject.api.auth.user.service.AuthService;
import com.toyproject.api.common.DefaultResponse;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.tomcat.util.http.SameSiteCookies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/user")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AuthController {

    @Autowired
    private AuthService authService;

    @ApiOperation("회원가입")
    @PostMapping("/sign-up")
    public ResponseEntity<Object> signUp (@RequestBody AuthDto.signUp user) {
        //입력한 비밀번호 암호화
        String encryPwd = DigestUtils.sha256Hex(user.getPwd());
        user.setPwd(encryPwd);

        try {
            authService.insertUser(user);

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
    public ResponseEntity<Object> signIn (@RequestBody AuthDto.signIn user) {
        //입력한 비밀번호 암호화
        String encryPwd = DigestUtils.sha256Hex(user.getPwd());
        user.setPwd(encryPwd);

        AuthDto.signIn userInfo = authService.getUser(user);

        if(userInfo == null) {
            return DefaultResponse.from(BAD_REQUEST.value(), "아이디 또는 비밀번호가 맞지않습니다.", user).build();
        }

        return DefaultResponse.from(OK.value(), "로그인 성공", userInfo).build();
    }

    @ApiOperation("로그아웃")
    @PostMapping("/sign-out")
    public ResponseEntity<Object> signOut (@CookieValue Cookie uid, HttpServletResponse response) {
        if(uid == null){
            return DefaultResponse.from(BAD_REQUEST.value(), "옳바르지 않은 요청입니다.", uid).build();
        }

        expireCookie(response, uid);

        return DefaultResponse.from(OK.value(), "로그아웃 성공", uid).build();
    }

    private void expireCookie(HttpServletResponse response, Cookie uid) {
        uid.setMaxAge(0);
        response.addCookie(uid);
    }
}
