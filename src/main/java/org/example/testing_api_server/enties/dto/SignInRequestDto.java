package org.example.testing_api_server.enties.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SignInRequestDto {
    @NotBlank(message = "username is required!")
    private String username;

    @NotBlank(message = "Password is required!")
    private String password;
}