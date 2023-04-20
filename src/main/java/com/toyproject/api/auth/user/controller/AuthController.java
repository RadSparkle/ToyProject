package com.toyproject.api.auth.user.controller;

import com.toyproject.api.auth.user.dto.AuthDto;
import com.toyproject.api.auth.user.service.AuthService;
import com.toyproject.api.common.DefaultResponse;
import com.toyproject.api.common.model.auth.TokenVo;
import com.toyproject.util.jwt.JwtPayLoad;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import static com.toyproject.util.StatusMsg.AUTH_MESSAGE_0007;
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
    @PostMapping("/users/signUp")
    @CrossOrigin("*")
    public ResponseEntity<Object> signUp (@RequestBody AuthDto.signUp user) {
        //입력한 비밀번호 암호화
        String encryPwd = DigestUtils.sha256Hex(user.getPwd());
        user.setPwd(encryPwd);

        try {
            authService.userAdd(user);

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
    @PostMapping("/login")
    @CrossOrigin("*")
    public ResponseEntity<Object> login (@RequestBody AuthDto.signIn user) throws Exception {
        //입력한 비밀번호 암호화
        String encryPwd = DigestUtils.sha256Hex(user.getPwd());
        user.setPwd(encryPwd);

        AuthDto.signIn userInfo = authService.userDetails(user);
        TokenVo token = authService.getToken(JwtPayLoad.builder()
                .uid(userInfo.getUid())
                .userId(userInfo.getUserId())
                .accessType(userInfo.getAccessTp())
                .userType(user.getUserType())
                .build());

        AuthDto.login vo = new AuthDto.login();

        vo.setUser_tp(userInfo.getUserType());
        vo.setAccess_token(token.getAccess_token());
        vo.setAccess_token_expires_in(token.getAccess_token_expires_in());
        vo.setRefresh_token(token.getRefresh_token());
        vo.setRefresh_token_expires_in(token.getRefresh_token_expires_in());
        vo.setAccess_tp(userInfo.getAccessTp());
        vo.setUid(userInfo.getUid());
        vo.setStatus(AUTH_MESSAGE_0007.getStatus_code());
        vo.setMessage(AUTH_MESSAGE_0007.getCustom_msg());


        if(userInfo == null) {
            return DefaultResponse.from(BAD_REQUEST.value(), "아이디 또는 비밀번호가 맞지않습니다.", user).build();
        }

        return DefaultResponse.from(OK.value(), "로그인 성공", vo).build();
    }

    @ApiOperation("회원 정보 조회")
    @GetMapping("/getUserInfo")
    @CrossOrigin("*")
    public void userInfo(@RequestHeader(name = "Authorization") String accessToken) throws Exception {
//        AuthDto.signIn userInfo = authService.getUserInfo(uid);
//
//        if(userInfo == null) {
//            return DefaultResponse.from(BAD_REQUEST.value(), "잘못된 uid 입니다.",uid).build();
//        }


//        return DefaultResponse.from(OK.value(), "개인정보 조회 성공", userInfo).build();
    }

    @ApiOperation("로그아웃")
    @PostMapping("/logout")
    @CrossOrigin("*")
    public ResponseEntity<Object> logout () {
        //JWT 로그아웃은 프론트에서 처리
        return DefaultResponse.from(OK.value(), "로그아웃 성공").build();
    }

    private void expireCookie(HttpServletResponse response, Cookie uid) {
        uid.setMaxAge(0);
        response.addCookie(uid);
    }
}
