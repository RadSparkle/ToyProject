package com.toyproject.api.auth.token.controller;

import com.toyproject.api.auth.token.service.TokenService;
import com.toyproject.api.common.model.auth.TokenInfoVo;
import com.toyproject.util.ResponseMessage;
import com.toyproject.util.StatusMsg;
import com.toyproject.util.jwt.JwtProvider;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/api/v1/token")
@RequiredArgsConstructor
public class TokenController {
    private final TokenService tokenService;
    private final ResponseMessage responseMessage;

    public ResponseEntity<TokenInfoVo> getToken(
            HttpServletRequest request,
            HttpServletResponse response,
            @RequestHeader(name = "Authorization") String accessToken) throws IOException {
        JwtProvider provider = new JwtProvider();
        Claims claims = provider.claimsChk(accessToken);
        if(claims == null) responseMessage.errorResponse(request, response, StatusMsg.COMMON_MESSAGE_0004);
        if(provider.tokenExpired(claims)) responseMessage.errorResponse(request, response, StatusMsg.AUTH_MESSAGE_0003);
        if(provider.getAccessToken(claims) != null) responseMessage.errorResponse(request, response, StatusMsg.AUTH_MESSAGE_0004);
        if(response.getStatus() < 200 || response.getStatus() > 299) return null;

    }

}
