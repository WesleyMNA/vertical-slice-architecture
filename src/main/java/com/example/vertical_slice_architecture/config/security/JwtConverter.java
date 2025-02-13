package com.example.vertical_slice_architecture.config.security;

import com.example.vertical_slice_architecture.shared.auth.CurrentUser;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

@Component
public class JwtConverter implements Converter<Jwt, CurrentUser> {

    @Override
    public CurrentUser convert(@NotNull Jwt jwt) {
        return new CurrentUser(jwt);
    }
}
