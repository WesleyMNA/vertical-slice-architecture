package com.example.vertical_slice_architecture.user.features.login;

import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@RequiredArgsConstructor
@Component
final class TokenGenerator {

    private final JwtEncoder encoder;

    String generate(final UUID id) {
        var now = Instant.now();
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("vertical-slice")
                .issuedAt(now)
                .expiresAt(now.plus(1, ChronoUnit.DAYS))
                .subject(id.toString())
                .build();
        return encoder
                .encode(JwtEncoderParameters.from(claims))
                .getTokenValue();
    }
}
