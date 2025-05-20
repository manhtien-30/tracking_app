package org.example.testing_api_server.enties.dto;

public record LoginInfo(
        String username,
        String password,
        String token) {
}
