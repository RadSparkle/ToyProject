package com.toyproject.api.auth.token.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.toyproject.api.auth.token.service.TokenService;
import com.toyproject.api.common.model.auth.TokenInfoVo;
import com.toyproject.util.jwt.JwtProvider;
import io.jsonwebtoken.Claims;

public class TokenServiceImpl implements TokenService {
    @Override
    public TokenInfoVo getTokenInfo(JwtProvider provider, Claims claims) {
        ObjectMapper mapper = new ObjectMapper();
        TokenInfoVo vo = new TokenInfoVo();
        if (provider.getUid(claims).isPresent()) {
            vo.setUid(provider.getUid(claims).get());
        }
        vo.setUser_id(provider.getUserId(claims));
        vo.setAccess_tp(provider.getAccessTp(claims));
        vo.setUser_tp(provider.getUserTp(claims));
        vo.setAccess_token_expires_in(mapper.convertValue(provider.getExpSec(claims), Integer.class));
        return vo;
    }
}
