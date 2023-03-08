package com.toyproject.api.contents.board.dto;


import lombok.Data;
import lombok.ToString;

import java.util.List;

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
        private String boardLink;
        private List pidList;
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

        private Object cmtList;
    }

    @Data
    public static class boardLike {
        private int pid;
        private int bid;
        private int uid;
        private int likeType;
    }

    @Data
    public static class boardCmt {
        private int uid;

        private int bid;

        private int pid;

        private int cid;

        private String contents;

        private String regDt;

        private String modDt;
    }
}
