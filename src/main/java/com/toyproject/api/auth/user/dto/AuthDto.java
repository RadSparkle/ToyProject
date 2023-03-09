package com.toyproject.api.auth.user.dto;

import com.toyproject.api.common.model.auth.TokenVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class AuthDto{

    @Data
    public static class signUp{
        private Long uid;
        private String userId;
        private String pwd;
        private String nickname;
        private int gender;
        private String birthday;
        private String accessTp;
        private int locationYn;
        private String userType;
    }

    @Data
    public static class signIn{
        private Long uid;
        private String userId;
        private String pwd;
        private String nickname;
        private int gender;
        private String birthday;
        private String accessTp;
        private int locationYn;
        private String userType;
    }

    @Data
    public static class login extends TokenVo {
        @ApiModelProperty(value = "사용자 유형", example = "U")
        private String user_tp;

        @ApiModelProperty(value = "uid")
        private Long uid;

        @ApiModelProperty(value = "access_tp")
        private String access_tp;

    }
}
