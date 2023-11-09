package com.B2B.EcommerceApp.OrderService.advisor;

import com.B2B.EcommerceApp.OrderService.exception.DuplicateKeyException;
import com.B2B.EcommerceApp.OrderService.exception.NotFoundException;
import com.B2B.EcommerceApp.OrderService.util.StandardResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppWideExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<StandardResponse> handleNotFoundException(NotFoundException e) {
        return new ResponseEntity<>(
                new StandardResponse(404, e.getMessage(), e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public ResponseEntity<StandardResponse> handleDuplicationKeyException(DuplicateKeyException e) {
        return new ResponseEntity<>(
                new StandardResponse(409, e.getMessage(), e.getMessage()), HttpStatus.CONFLICT);
    }
}
