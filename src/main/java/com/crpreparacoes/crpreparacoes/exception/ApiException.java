package com.crpreparacoes.crpreparacoes.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ApiException {
    private final String message;
    private final HttpStatus httpStatus;

    public ApiException(String message,  HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
