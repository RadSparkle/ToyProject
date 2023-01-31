package com.toyproject.api.auth.user.dto;

import com.toyproject.api.auth.user.jwt.dto.TokenInfo;
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
    public static class signIn {
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

//    @Data
//    public static class signInInfo  extends TokenInfo{
//        private int uid;
//        private String userId;
//    }
}
