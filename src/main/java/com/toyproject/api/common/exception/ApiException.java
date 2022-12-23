package com.toyproject.api.common.exception;

import lombok.Getter;

@Getter
public class ApiException extends RuntimeException {

    private final EnumException error;

    public ApiException(EnumException e) {
        super(e.getMessage());
        this.error = e;
    }

    public ApiException(EnumException e, String customMessage) {
        super(e.getMessage());
        e.setCustomMessage(customMessage);
        this.error = e;
    }
}