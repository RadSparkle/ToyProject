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
    COMMON_MESSAGE_0006(HttpStatus.CONFLICT.value(), HttpStatus.CONFLICT.getReasonPhrase(), "The requested information is duplicated."),

    /* 인증 메시지 */
    AUTH_MESSAGE_0001(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase(), "This user is not found."),
    AUTH_MESSAGE_0002(HttpStatus.FORBIDDEN.value(), HttpStatus.FORBIDDEN.getReasonPhrase(), "Password is incorrect."),
    AUTH_MESSAGE_0003(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.getReasonPhrase(), "Sorry, your token expired."),
    AUTH_MESSAGE_0004(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), "This token is not access token."),
    AUTH_MESSAGE_0005(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), "This token is not refresh token."),
    AUTH_MESSAGE_0006(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), "This token is wrong."),
    AUTH_MESSAGE_0007(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), "Login success."),
    AUTH_MESSAGE_0008(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), "Logout success"),
    AUTH_MESSAGE_0009(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), "Token lookup success."),
    AUTH_MESSAGE_0010(HttpStatus.CREATED.value(), HttpStatus.CREATED.getReasonPhrase(), "Token create success."),
    AUTH_MESSAGE_0011(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), "If access_ch is 'M', app_ver is required."),
    AUTH_MESSAGE_0012(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), "Guest account creation failure."),
    AUTH_MESSAGE_0013(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), "Guest account login failure."),
    AUTH_MESSAGE_0014(HttpStatus.CREATED.value(), HttpStatus.CREATED.getReasonPhrase(), "Guest token creation success."),
    AUTH_MESSAGE_0015(HttpStatus.FORBIDDEN.value(), HttpStatus.FORBIDDEN.getReasonPhrase(), "This user is retired user."),
    AUTH_MESSAGE_0016(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), "Temporary password sending was success."),
    AUTH_MESSAGE_0017(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), "Inactive user login.");

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
