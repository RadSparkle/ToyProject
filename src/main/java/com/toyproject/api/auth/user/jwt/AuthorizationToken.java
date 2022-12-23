package com.toyproject.api.auth.user.jwt;

import com.toyproject.api.common.DefaultResponse;
import com.toyproject.api.common.exception.ApiException;
import com.toyproject.api.common.exception.EnumException;
import lombok.EqualsAndHashCode;

import java.util.Optional;

import static com.toyproject.api.common.exception.EnumException.TOKEN_IS_WRONG;

@EqualsAndHashCode
public class AuthorizationToken {
    private final String authorization;

    public static final String HEADER_AUTH_KEY = "Authorization";

    private AuthorizationToken(String authorization) {
        if (authorization.isBlank()) {
            throw new ApiException(EnumException.TOKEN_IS_WRONG);
        }
        this.authorization = authorization;
    }

    public static AuthorizationToken of(String authorization) {
        Optional<String> stringOptional = Optional.ofNullable(authorization);

        if (stringOptional.isPresent()) {
            return new AuthorizationToken(stringOptional.get());
        }
        throw new ApiException(TOKEN_IS_WRONG);
    }

//    public static AuthorizationToken verify(String authorization) {
//
//    }

}
