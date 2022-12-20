package com.toyproject.api.auth.user.dto;

import lombok.Data;

public class AuthDto {

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
    public static class signIn{
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
}
