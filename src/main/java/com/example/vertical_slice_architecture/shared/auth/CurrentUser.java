package com.example.vertical_slice_architecture.shared.auth;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.util.List;
import java.util.UUID;

@Getter
@ToString
@EqualsAndHashCode(of = "id", callSuper = false)
public class CurrentUser extends JwtAuthenticationToken {

    private final UUID id;

    public CurrentUser(Jwt jwt) {
        super(jwt, List.of());
        this.id = UUID.fromString(jwt.getClaimAsString("sub"));
    }
}
