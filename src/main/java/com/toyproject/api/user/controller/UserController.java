package com.toyproject.api.user.controller;

import com.toyproject.api.common.DefaultResponse;
import com.toyproject.api.common.model.auth.TokenInfoVo;
import com.toyproject.api.common.model.auth.UserVo;
//import com.toyproject.api.common.service.auth.CommonAuthService;
import com.toyproject.api.user.service.UserService;
import com.toyproject.util.jwt.JwtProvider;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    private static JwtProvider jwtProvider;

    @ApiOperation("마이페이지 조회")
    @GetMapping("/getMyPage")
    @CrossOrigin("*")
    public ResponseEntity<Object> getMyPage(HttpServletResponse response, HttpServletRequest request,
                                            @RequestHeader(name = "Authorization") String accessToken) throws Exception {
        int uid = jwtProvider.getUid(accessToken);

        HashMap userVo = (HashMap) userService.getMyPage(uid);


        return DefaultResponse.from(OK.value(),"마이페이지 조회 성공", userVo).build();
    }

    @ApiOperation("팔로우 하기")
    @PostMapping("/follow/{fid}")
    @CrossOrigin("*")
    public ResponseEntity<Object> following(HttpServletRequest request, HttpServletResponse response,
                                            @PathVariable int fid,
                                            @RequestHeader(name = "Authorization") String accessToken) throws Exception {
        int uid = jwtProvider.getUid(accessToken);

        userService.insertFollow(uid, fid);

        return DefaultResponse.from(OK.value(), "팔로잉 성공", fid).build();
    }
}
