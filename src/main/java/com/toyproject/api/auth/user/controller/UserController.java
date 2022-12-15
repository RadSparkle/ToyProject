package com.toyproject.api.auth.user.controller;

import com.toyproject.api.auth.user.dto.UserDto;
import com.toyproject.api.auth.user.service.UserService;
import com.toyproject.api.auth.user.service.impl.UserServiceImpl;
import com.toyproject.api.common.DefaultResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.ibatis.ognl.ObjectElementsAccessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.service.ResponseMessage;

import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/user")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/sign-up")
    public ResponseEntity<Object> signUp (@RequestBody UserDto.signUp user) {
        //비밀번호 암호화
        String encryPwd = DigestUtils.sha256Hex(user.getPwd());
        user.setPwd(encryPwd);


        try {
            userService.insertUser(user);
        } catch (DuplicateKeyException e) {
            return DefaultResponse.from(BAD_REQUEST.value(),"아이디 중복").build();

        }

        return DefaultResponse.from(CREATED.value(),"아이디 생성 성공").build();
    }
}
