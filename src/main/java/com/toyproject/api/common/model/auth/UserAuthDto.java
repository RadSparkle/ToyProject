package com.toyproject.api.common.model.auth;

import com.sun.istack.NotNull;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserAuthDto {

    @NotNull
    @ApiModelProperty(value = "사용자 아이디")
    private String user_id;

    @ApiModelProperty(value = "액세스 유형", example = "Z")
    private String access_tp;
}