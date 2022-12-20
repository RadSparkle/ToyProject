package com.toyproject.api.user.dto;

import lombok.Data;

public class UserDto {

    @Data
    public static class myPageInfo{
        private int uid;
        private String userId;
        private String nickname;
        private int gender;
        private String birthday;
    }
}
