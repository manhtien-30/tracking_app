package org.example.testing_api_server.enties.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class ApiResponseDto<T> {
    private String status;
    private String message;
    private T response;
}
