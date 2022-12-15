package com.toyproject.api.contents.board.dto;


import lombok.Data;

public class BoardDto {

    @Data
    public static class boardList {
        private int pid;
        private int bid;
        private String title;
        private int views;
        private int cmtCount;
        private int isUse;
        private String regDt;
        private String contents;
        private String boardName;
        private int likeCount;
    }

    @Data
    public static class boardInfo {
        private int pid;
        private int bid;
        private int uid;
        private String title;
        private String nickname;
        private String bLink;
        private String imgLink;
        private int isUse;
        private int isNotice;
        private String regDt;
        private String modDt;
        private String contents;
        private String boardName;
    }

    @Data
    public static class boardLike {
        private int pid;
        private int bid;
        private int uid;
        private int likeType;
    }
}
