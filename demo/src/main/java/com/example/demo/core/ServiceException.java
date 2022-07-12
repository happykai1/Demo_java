package com.example.demo.core;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import javax.validation.ConstraintViolation;
import java.util.Set;

@Getter
public class ServiceException extends RuntimeException {

    private final HttpStatus statusCode;
    private final String errorCode;
    private final Object error;

    public ServiceException(HttpStatus statusCode, Throwable cause, Object error, String errCode, String errMessage, Object... args){
        super(String.format(errMessage, args), cause);
        this.statusCode = statusCode;
        this.errorCode = errCode;
        this.error = error;
    }

    public ServiceException(HttpStatus statusCode, ErrorData errorData){
        super(errorData.getMsg());
        this.statusCode = statusCode;
        this.errorCode = errorData.getCode();
        this.error = errorData.getError();
    }

}