package org.example.testing_api_server.enties.dto;

import lombok.Builder;
import lombok.Data;

import java.time.OffsetDateTime;
import java.util.List;

@Data
@Builder
public class SignInResponseDto {
    private String token;
    private String type = "Bearer";
    private Long id;
    private String username;
    private String email;
    @Builder.Default
    private OffsetDateTime lastLogin = OffsetDateTime.now();
    private List<String> roles;
}