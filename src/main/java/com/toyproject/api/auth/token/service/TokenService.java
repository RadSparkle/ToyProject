package com.toyproject.api.auth.token.service;

import com.toyproject.api.common.model.auth.TokenInfoVo;
import com.toyproject.util.jwt.JwtProvider;
import io.jsonwebtoken.Claims;

public interface TokenService {

    TokenInfoVo getTokenInfo(JwtProvider provider, Claims claims);
}
