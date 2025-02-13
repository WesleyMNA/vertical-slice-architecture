package com.example.vertical_slice_architecture.shared.auth;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;

public class AuthHelper {

    private AuthHelper() {
    }

    public static CurrentUser getCurrentUser() {
        Jwt jwt = (Jwt) getAuthentication().getPrincipal();
        return new CurrentUser(jwt);
    }

    private static Authentication getAuthentication() {
        return SecurityContextHolder
                .getContext()
                .getAuthentication();
    }
}
