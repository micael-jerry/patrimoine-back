package com.patrimoine.rattrapage.controller.exeption;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@Builder
@ToString
public class ApiResponse {
    private Integer code;
    private HttpStatus status;
    private String message;
}