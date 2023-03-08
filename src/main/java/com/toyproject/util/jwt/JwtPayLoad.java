package com.toyproject.util.jwt;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

@Getter
@Setter
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

    private long exp;

    public void setExp(long exp) {
        this.exp = exp;
    }

    public Map<String, Object> getMap() {
        Map<String, Object> resultMap = new LinkedHashMap<>();
        resultMap.put("uid", this.uid);
        resultMap.put("user_id", this.userId);
        resultMap.put("access_tp", this.accessType);
        resultMap.put("user_tp", this.userType);
        resultMap.put("exp", this.exp);
        return Collections.unmodifiableMap(resultMap);
    }
}
