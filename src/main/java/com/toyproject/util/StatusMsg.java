package com.toyproject.util;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public enum StatusMsg {
    /* 공통 메시지 */
    COMMON_MESSAGE_0001(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), "Check your request header."),
    COMMON_MESSAGE_0002(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), "Check your request parameter."),
    COMMON_MESSAGE_0003(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), "Check your request body."),
    COMMON_MESSAGE_0004(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.getReasonPhrase(), "Unauthenticated user."),
    COMMON_MESSAGE_0005(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), "This room is not yours."),
    COMMON_MESSAGE_0006(HttpStatus.CONFLICT.value(), HttpStatus.CONFLICT.getReasonPhrase(), "The requested information is duplicated.");

    @Getter
    private int status_code;
    @Getter
    private String status_msg;
    @Getter
    private String custom_msg;

    StatusMsg(int status_code, String status_msg, String custom_msg) {
        this.status_code = status_code;
        this.status_msg = status_msg;
        this.custom_msg = custom_msg;
    }
}
