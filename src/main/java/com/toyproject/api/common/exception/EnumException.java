package com.toyproject.api.common.exception;

import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Getter
@ToString
public enum EnumException {

    //인증
    TOKEN_IS_WRONG(BAD_REQUEST, "A0001", "발급된 엑세스 토큰이 잘못 되었습니다.");

    private final HttpStatus status;

    private final String code;

    private String message;

    EnumException(HttpStatus status, String code) {
        this.status = status;
        this.code = code;
    }

    EnumException(HttpStatus status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public String getCode() {
        return code;
    }

    public void setCustomMessage(String message) {
        this.message = message;
    }
}
