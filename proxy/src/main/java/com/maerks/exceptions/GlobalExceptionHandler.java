package com.maerks.exceptions;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice(basePackages = "com.maerks")
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity handleAllExceptions(final Exception exception) {
        return handleException(exception, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(InvalidFormatException.class)
    public ResponseEntity invalidFormatException(final InvalidFormatException exception) {
        return handleException(exception, HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity handleException(Exception exception, HttpStatus status) {
        Map<String, String> errorMap = new LinkedHashMap<>();
        errorMap.put("title", status.getReasonPhrase());
        errorMap.put("message", exception.getMessage());
        return new ResponseEntity<>(errorMap, status);
    }

    @Override
    protected ResponseEntity handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        BindingResult bindingResult = ex.getBindingResult();
        List<ObjectError> errorList = bindingResult.getAllErrors();
        Map<String, String> errorMap = new LinkedHashMap<>();
        errorMap.put("title", status.getReasonPhrase());
        for (ObjectError objectError : errorList) {
            errorMap.put("message", objectError.getDefaultMessage());
        }
        return new ResponseEntity<>(errorMap, headers, status);
    }


}
