package org.example.testing_api_server.services;

import org.example.testing_api_server.enties.dto.ApiResponseDto;
import org.example.testing_api_server.enties.dto.SignInRequestDto;
import org.example.testing_api_server.enties.dto.SignInResponseDto;
import org.example.testing_api_server.enties.dto.SignUpRequestDto;
import org.example.testing_api_server.utils.exceptions.RoleNotFoundException;
import org.example.testing_api_server.utils.exceptions.UserAlreadyExistsException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface AuthenticatedService {
    ResponseEntity<ApiResponseDto<?>> signUp(SignUpRequestDto signUpRequestDto) throws UserAlreadyExistsException, RoleNotFoundException;

    ResponseEntity<ApiResponseDto<?>> signIn(SignInRequestDto signInRequestDto);
}