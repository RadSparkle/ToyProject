package com.toyproject.api.common;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode
@Getter
public class DefaultResponse<T> {

    private final int status;
    private final String message;
    private final Long totalCount;
    private final List<T> data;

    private DefaultResponse(Integer status, String message, Long totalCount, List<T> data) {
        this.status = status;
        this.message = message;
        this.data = data;
        this.totalCount = totalCount;
    }

    public static<T> DefaultResponse<T> from(final Integer status, final String message, final List<T> data) {
        return new DefaultResponse<T>(status, message, Long.parseLong(String.valueOf(data.size())), data);
    }

    public static<T> DefaultResponse<T> from(final Integer status, final String message) {
        return new DefaultResponse<T>(status, message, 0L, new ArrayList<>());
    }

    public static<T> DefaultResponse<T> from(final Integer status, final String message, final T data) {
        List<T> dataList = new ArrayList<>();
        dataList.add(data);
        return new DefaultResponse<T>(status, message, Long.parseLong(String.valueOf(dataList.size())), dataList);
    }

    public ResponseEntity<Object> build() {
        return ResponseEntity
                .status(status)
                .body(this);
    }
}
