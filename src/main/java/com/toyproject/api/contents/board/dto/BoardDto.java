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
}
