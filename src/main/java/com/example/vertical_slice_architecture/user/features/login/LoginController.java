package com.example.vertical_slice_architecture.user.features.login;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/login")
class LoginController {

    private final LoginService service;

    @PostMapping
    ResponseEntity<LoginResponse> login(
            @RequestBody
            @Valid
            LoginRequest request
    ) {
        LoginResponse res = service.login(request);
        return ResponseEntity.ok(res);
    }
}
