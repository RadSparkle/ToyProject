package com.toyproject.api.user.controller;

import com.toyproject.api.common.DefaultResponse;
import com.toyproject.api.user.dto.UserDto;
import com.toyproject.api.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;

import java.util.HashMap;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation("마이페이지 조회")
    @GetMapping("/getMyPage")
    public ResponseEntity<Object> getMyPage(@CookieValue(value="uid") Cookie uid, @RequestParam(required = false) int bid) {
        System.out.println(bid);
        HashMap myPageInfo = userService.getMyPage(uid.getValue(), bid);

        return DefaultResponse.from(OK.value(),"마이페이지 조회 성공", myPageInfo).build();
    }

//    @ApiOperation("내가 쓴 게시글 리스트 조회")
//    @GetMapping("/getMyBoardList")
//    public ResponseEntity<Object> getMyBoardList()
}
