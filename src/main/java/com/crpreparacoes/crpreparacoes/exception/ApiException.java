package com.crpreparacoes.crpreparacoes.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ApiException {
    private final String message;
    private final HttpStatus httpStatus;

    public ApiException(String message,  HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
