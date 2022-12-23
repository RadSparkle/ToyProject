package com.toyproject.api.auth.user.jwt.dto;

import lombok.*;

@Data
@Getter
@Setter
public class TokenInfo {
    private String accessToken;

    private String accessTokenExpiration;

    private String refreshToken;

    private String refreshTokenExpiration;

    private String tokenType;
}
