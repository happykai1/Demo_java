package com.example.demo.app.exception;

import com.example.demo.core.ResponseStatus;
import com.example.demo.core.ServiceException;
import org.springframework.http.HttpStatus;

import javax.validation.ConstraintViolation;
import java.util.Set;
import java.util.stream.Collectors;

public class DemoException extends ServiceException {
    public DemoException(HttpStatus statusCode, String errCode, String errMessage, Object... args){
        super(statusCode, null, null, errCode, errMessage, args);
    }

    public DemoException(HttpStatus statusCode, ResponseStatus status, Object... args){
        super(statusCode, null, null, status.getCode(), status.getMessage(), args);
    }

    public DemoException(HttpStatus statusCode, Throwable cause, ResponseStatus status, Object... args){
        super(statusCode, cause, null, status.getCode(), status.getMessage(), args);
    }

    public DemoException(HttpStatus statusCode, Object error, ResponseStatus status, Object... args){
        super(statusCode, null, error, status.getCode(), status.getMessage(), args);
    }

    public DemoException(HttpStatus statusCode, Object error, String errCode, String errMessage, Object... args){
        super(statusCode, null, error, errCode, errMessage, args);
    }

    public DemoException(Set<? extends ConstraintViolation<?>> violations) {
        super(HttpStatus.BAD_REQUEST,
                null,
                null,
                ResponseStatus.INVALID_REQUEST_PARAMETER.getCode(),
                violations != null ? ResponseStatus.INVALID_REQUEST_PARAMETER.getMessage() + renderText(violations) : null);
    }

    private static String renderText(Set<? extends ConstraintViolation<?>> violations) {
        var text = violations.stream()
                .map(v -> v == null ? "null" : String.format("'%s' %s", convertSnakeCase(v.getPropertyPath().toString()), v.getMessage()) + " |" )
                .collect(Collectors.joining(" "));

        return text.substring(0, text.length() - 2);
    }

    private static String convertSnakeCase(String str) {
        return str.replaceAll("([a-z])([A-Z]+)", "$1_$2").toLowerCase();
    }
}
