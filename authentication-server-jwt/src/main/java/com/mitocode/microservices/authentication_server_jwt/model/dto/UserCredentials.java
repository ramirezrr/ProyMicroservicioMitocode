package com.mitocode.microservices.authentication_server_jwt.model.dto;

public record UserCredentials(
        String username,
        String password
) {
}
