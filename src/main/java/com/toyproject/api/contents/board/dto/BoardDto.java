package com.toyproject.api.contents.board.dto;


import lombok.Data;

public class BoardDto {

    @Data
    public static class boardList {
        private int bid;
        private int bSeq;
        private String title;
        private int views;
        private int cmtCount;
        private int isUse;
        private String regDt;
        private int boardCount;
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
        private String bContents;
    }
}
