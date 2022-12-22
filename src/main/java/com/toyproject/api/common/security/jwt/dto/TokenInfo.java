package com.toyproject.api.common.security.jwt.dto;

import lombok.*;

@Data
@Getter
@Setter
public class TokenInfo {
    private String accessToken;

    private String refreshToken;

    private String grantType;
}
