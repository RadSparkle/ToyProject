package com.toyproject.api.common.service.auth;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.toyproject.api.common.mapper.auth.CommonAuthMapper;
import com.toyproject.api.common.model.auth.TokenInfoVo;
import com.toyproject.api.common.model.auth.UserVo;
import com.toyproject.util.RestTemplateUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;

import static com.toyproject.util.jwt.JwtProvider.isBearer;
@Service("CommonAuthService")
@RequiredArgsConstructor
public class CommonAuthServiceImpl implements CommonAuthService{

    private final CommonAuthMapper commonAuthMapper;

    @Value(value = "${toycont.auth.api.v1.token}")
    private String authApiV1TokenUrl;

    @Override
    public TokenInfoVo getTokenInfo(String accessToken) throws Exception {
        if (isBearer(accessToken)) {
            accessToken = accessToken.substring(7);
        }

        ObjectMapper mapper = new ObjectMapper();

        Object jsonResult = RestTemplateUtils.getJsonRequest(authApiV1TokenUrl, accessToken);
        if (jsonResult == null) {
            return null;
        }
        Map<String, Object> resultMap = mapper.convertValue(jsonResult, new TypeReference<Map<String, Object>>() {});

        if (resultMap.containsKey("error")) {
            return null;
        }

        return mapper.convertValue(resultMap, TokenInfoVo.class);
    }

    @Override
    public UserVo getUser(Long uid) {
        return commonAuthMapper.getUser(uid);
    }
}
