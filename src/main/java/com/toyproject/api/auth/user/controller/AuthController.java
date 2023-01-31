package com.toyproject.api.auth.user.controller;

import com.toyproject.api.auth.user.dto.AuthDto;
import com.toyproject.api.auth.user.jwt.AuthorizationToken;
import com.toyproject.api.auth.user.service.AuthService;
import com.toyproject.api.common.DefaultResponse;
import com.toyproject.api.auth.user.jwt.JwtProvider;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private JwtProvider jwtProvider;

    @ApiOperation("회원가입")
    @PostMapping("/sign-up")
    @CrossOrigin("*")
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

//    @ApiOperation("로그인")
//    @PostMapping("/sign-in")
//    @CrossOrigin("*")
//    public ResponseEntity<Object> signIn (@RequestBody AuthDto.signIn user) {
//        //입력한 비밀번호 암호화
//        String encryPwd = DigestUtils.sha256Hex(user.getPwd());
//        user.setPwd(encryPwd);
//
//        AuthDto.signInInfo userInfo = authService.getUser(user);
//
//        if(userInfo == null) {
//            return DefaultResponse.from(BAD_REQUEST.value(), "아이디 또는 비밀번호가 맞지않습니다.", user).build();
//        }
//
//        userInfo.setAccessToken(jwtProvider.createToken(userInfo.getUserId()));
//
//        return DefaultResponse.from(OK.value(), "로그인 성공", userInfo).build();
//    }

    @ApiOperation("회원 정보 조회")
    @GetMapping("/getUserInfo/{uid}")
    @CrossOrigin("*")
    public ResponseEntity<Object> userInfo(@PathVariable int uid, HttpServletRequest request) {
        AuthDto.signIn userInfo = authService.getUserInfo(uid);

        if(userInfo == null) {
            return DefaultResponse.from(BAD_REQUEST.value(), "잘못된 uid 입니다.",uid).build();
        }

        AuthorizationToken.of(request.getHeader(AuthorizationToken.HEADER_AUTH_KEY));

        return DefaultResponse.from(OK.value(), "개인정보 조회 성공", userInfo).build();
    }

    @ApiOperation("로그아웃")
    @PostMapping("/sign-out")
    @CrossOrigin("*")
    public ResponseEntity<Object> signOut () {
        //JWT 로그아웃은 프론트에서 처리
        return DefaultResponse.from(OK.value(), "로그아웃 성공").build();
    }

    private void expireCookie(HttpServletResponse response, Cookie uid) {
        uid.setMaxAge(0);
        response.addCookie(uid);
    }
}
