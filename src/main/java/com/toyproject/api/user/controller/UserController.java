package com.toyproject.api.user.controller;

import com.toyproject.api.common.DefaultResponse;
import com.toyproject.api.common.model.auth.TokenInfoVo;
import com.toyproject.api.common.model.auth.UserVo;
//import com.toyproject.api.common.service.auth.CommonAuthService;
import com.toyproject.api.user.service.UserService;
import com.toyproject.util.jwt.JwtProvider;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.HashMap;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {

    private final UserService userService;

    @ApiOperation("마이페이지 조회")
    @GetMapping("/getMyPage")
    public ResponseEntity<Object> getMyPage(HttpServletResponse response, HttpServletRequest request,
                                            @RequestHeader(name = "Authorization") String accessToken) throws Exception {
        JwtProvider jwt = new JwtProvider();
        int uid = (int)jwt.claimsChk(accessToken).get("uid");

        HashMap userVo = (HashMap) userService.getMyPage(uid);


        return DefaultResponse.from(OK.value(),"마이페이지 조회 성공", userVo).build();
    }

//    @ApiOperation("내가 쓴 게시글 리스트 조회")
//    @GetMapping("/getMyBoardList")
//    public ResponseEntity<Object> getMyBoardList()
}
