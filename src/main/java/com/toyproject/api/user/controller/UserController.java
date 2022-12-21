package com.toyproject.api.user.controller;

import com.toyproject.api.common.DefaultResponse;
import com.toyproject.api.user.service.UserService;
import io.swagger.annotations.ApiOperation;
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
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation("마이페이지 조회")
    @GetMapping("/getMyPage")
    public ResponseEntity<Object> getMyPage(@RequestParam int uid) {
        HashMap myPageInfo = userService.getMyPage(uid);

        return DefaultResponse.from(OK.value(),"마이페이지 조회 성공", myPageInfo).build();
    }

//    @ApiOperation("내가 쓴 게시글 리스트 조회")
//    @GetMapping("/getMyBoardList")
//    public ResponseEntity<Object> getMyBoardList()
}
