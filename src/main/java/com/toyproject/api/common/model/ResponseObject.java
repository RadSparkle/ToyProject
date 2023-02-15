package com.toyproject.api.common.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseObject {

    @ApiModelProperty(value = "상태코드", example = "200")
    private Integer status;

    @ApiModelProperty(value = "상태메시지", example = "정상입니다.")
    private String message;

}

