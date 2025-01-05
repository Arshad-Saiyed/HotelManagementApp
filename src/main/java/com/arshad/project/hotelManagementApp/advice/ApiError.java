package com.arshad.project.hotelManagementApp.advice;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
public class ApiError {
    private HttpStatus status;
    private String errorText;
}
