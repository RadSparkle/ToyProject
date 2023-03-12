package com.toyproject.api.common.service.auth;

import com.toyproject.api.common.model.auth.TokenInfoVo;
import com.toyproject.api.common.model.auth.UserVo;

public interface CommonAuthService {
    TokenInfoVo getTokenInfo(String accessToken) throws Exception;

    UserVo getUser(Long uid);
}
