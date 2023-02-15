package com.toyproject.api.auth.user.jwt;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class JwtPayLoad {
    private long uid;

    @JsonProperty("user_id")
    private String userId;

    @JsonProperty("access_tp")
    private String accessType;

    @JsonProperty("user_tp")
    private String userType;
}
