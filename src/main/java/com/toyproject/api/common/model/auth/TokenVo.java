package com.toyproject.api.common.model.auth;

import com.toyproject.api.common.model.ResponseObject;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class TokenVo extends ResponseObject {
    @ApiModelProperty(value = "액세스 토큰")
    private String access_token;

    @ApiModelProperty(value = "액세스 토큰 만료시간(초)")
    private Integer access_token_expires_in;

    @ApiModelProperty(value = "리프레시 토큰")
    private String refresh_token;

    @ApiModelProperty(value = "리프레시 토큰 만료시간(초)")
    private Integer refresh_token_expires_in;

    @ApiModelProperty(value = "토큰 유형", example = "bearer")
    private String token_tp = "bearer";
}
