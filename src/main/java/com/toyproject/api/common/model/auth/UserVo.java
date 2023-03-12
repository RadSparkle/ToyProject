package com.toyproject.api.common.model.auth;

import com.toyproject.api.common.model.ResponseObject;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class UserVo extends ResponseObject {

    @ApiModelProperty(value = "사용자번호(고유식별번호)", example = "1")
    private Long uid;

    @ApiModelProperty(value = "사용자 아이디(ID/이메일)")
    private String user_id;

    @ApiModelProperty(value = "액세스 타입", example = "Z")
    private String access_tp;

    @ApiModelProperty(value = "비밀번호")
    private String pwd;

    @ApiModelProperty(value = "사용자 권한", example = "U")
    private String user_tp;

    @ApiModelProperty(value = "생성일시(가입일시)")
    private String reg_dt;

    @ApiModelProperty(value = "수정일시")
    private String mod_dt;
}

