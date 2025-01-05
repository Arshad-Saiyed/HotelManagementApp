package com.arshad.project.hotelManagementApp.advice;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ApiResponse<T> {

    @JsonFormat(pattern = "hh-mm-ss dd-MM-yyyy")
    private LocalDateTime timestamp;
    private ApiError error;
    private T data;

    public ApiResponse() {
        this.timestamp = LocalDateTime.now();
    }

    public ApiResponse(ApiError error) {
        this();
        this.error = error;
    }

    public ApiResponse(T data) {
        this();
        this.data = data;
    }
}
