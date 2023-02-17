package com.toyproject.api.auth.user.dto;

import com.toyproject.api.auth.user.jwt.dto.TokenInfo;
import com.toyproject.api.common.model.auth.TokenVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class AuthDto{

    @Data
    public static class signUp{
        private int uid;
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
    public static class signIn extends TokenInfo {
        private int uid;
        private String userId;
        private String pwd;
        private String nickname;
        private int gender;
        private String birthday;
        private String accessTp;
        private int locationYn;
        private String userType;
    }

    public class LoginVo extends TokenVo {
        private String user_tp;

        private String notice_msg;

        private int is_activation;

        private Long uid;

        private String access_tp;

        private String access_id;
    }
}
