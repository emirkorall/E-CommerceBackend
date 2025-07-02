package com.ecommerce.exception;

import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalException {

  @ExceptionHandler(ApiException.class)
  public ResponseEntity<ExceptionResponse> handleApiException(ApiException ex) {
    log.error("API Exception occurred", ex);
    ExceptionResponse response =
        new ExceptionResponse(ex.getMessage(), ex.getHttpStatus().value(), LocalDateTime.now());
    return new ResponseEntity<>(response, ex.getHttpStatus());
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ExceptionResponse> handleGlobalException(Exception ex) {
    log.error("Unexpected error occurred", ex);
    ExceptionResponse response =
        new ExceptionResponse(
            "Internal server error occurred. Please contact support.",
            HttpStatus.INTERNAL_SERVER_ERROR.value(),
            LocalDateTime.now());
    return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
