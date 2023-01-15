package com.coremaker.coding.dto;

import lombok.Builder;
import org.springframework.http.HttpStatus;

@Builder
public class ApiResponse {

    private HttpStatus status;
    private String message;
    private String errorCode;

    public ApiResponse(final HttpStatus status, final String message, final String errorCode) {
        this.status = status;
        this.message = message;
        this.errorCode = errorCode;
    }
}
