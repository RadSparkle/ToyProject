package com.toyproject.api.auth.user.controller;

import com.toyproject.api.auth.user.dto.UserDto;
import com.toyproject.api.auth.user.service.UserService;
import com.toyproject.api.auth.user.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.ognl.ObjectElementsAccessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.service.ResponseMessage;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/sign-up")
    public ResponseEntity<Object> signUp (@RequestBody UserDto.signUp user) {
        userService.insertUser(user);

        return ResponseEntity.ok(user);
    }
}
