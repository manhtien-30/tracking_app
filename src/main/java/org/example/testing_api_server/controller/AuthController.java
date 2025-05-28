package org.example.testing_api_server.controller;

import jakarta.validation.Valid;
import org.example.testing_api_server.enties.dto.ApiResponseDto;
import org.example.testing_api_server.enties.dto.SignInRequestDto;
import org.example.testing_api_server.enties.dto.SignUpRequestDto;
import org.example.testing_api_server.services.impl.AuthenticatedServiceImpl;
import org.example.testing_api_server.utils.exceptions.RoleNotFoundException;
import org.example.testing_api_server.utils.exceptions.UserAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthenticatedServiceImpl authenticatedService;
    @PostMapping("/sign-up")
    public ResponseEntity<ApiResponseDto<?>> signUp(@RequestBody @Valid SignUpRequestDto signUpRequestDto)
            throws UserAlreadyExistsException, RoleNotFoundException {
        return authenticatedService.signUp(signUpRequestDto);
    }
    @PostMapping("/sign-in")
    public ResponseEntity<ApiResponseDto<?>> signIn(@RequestBody @Valid SignInRequestDto signInRequestDto) {
        return authenticatedService.signIn(signInRequestDto);
    }
}

