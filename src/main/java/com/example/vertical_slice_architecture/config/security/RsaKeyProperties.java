package com.example.vertical_slice_architecture.config.security;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@ConfigurationProperties("auth.rsa")
record RsaKeyProperties(RSAPublicKey publicKey, RSAPrivateKey privateKey) {
}
