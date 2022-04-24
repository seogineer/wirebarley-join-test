package com.seogineer.wirebarleyjointest.common.advice;

import com.seogineer.wirebarleyjointest.common.exception.BadRequestException;
import com.seogineer.wirebarleyjointest.common.exception.SubscribeException;
import com.seogineer.wirebarleyjointest.common.exception.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(SubscribeException.class)
    public ResponseEntity<ErrorResponse> handleSubscribeException(SubscribeException ex) {
        return ResponseEntity.internalServerError().body(new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage()));
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponse> handleBadRequestException(BadRequestException ex) {
        return ResponseEntity.badRequest().body(new ErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage()));
    }
}
