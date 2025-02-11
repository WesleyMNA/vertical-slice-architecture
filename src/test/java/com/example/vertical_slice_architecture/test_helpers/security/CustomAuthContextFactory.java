package com.example.vertical_slice_architecture.test_helpers.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

@RequiredArgsConstructor
class CustomAuthContextFactory implements WithSecurityContextFactory<WithCustomAuth> {

    private final JwtEncoder encoder;

    @Override
    public SecurityContext createSecurityContext(WithCustomAuth annotation) {
        var context = SecurityContextHolder.getContext();
        var now = Instant.now();
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("vertical-slice")
                .issuedAt(now)
                .expiresAt(now.plus(1, ChronoUnit.DAYS))
                .subject(annotation.id())
                .build();
        Jwt jwt = encoder
                .encode(JwtEncoderParameters.from(claims));
        var authentication = new JwtAuthenticationToken(jwt, List.of());
        context.setAuthentication(authentication);
        return context;
    }
}
