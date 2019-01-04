package com.example.jenkins.errorHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(UserDetailsAlreadyExistException.class)
  public final ResponseEntity<ErrorMessageDetails> handleUserAlreadyExist(
      UserDetailsAlreadyExistException ex, WebRequest request) {
    ErrorMessageDetails errorDetails =
        ErrorMessageDetails.builder()
            .timestamp(new Date())
            .message(ex.getMessage())
            .details(request.getDescription(true))
            .build();
    return new ResponseEntity<>(errorDetails, HttpStatus.CONFLICT);
  }

  @ExceptionHandler(Exception.class)
  public final ResponseEntity<ErrorMessageDetails> handle50X(Exception ex, WebRequest request) {
    ErrorMessageDetails errorDetails =
        ErrorMessageDetails.builder()
            .timestamp(new Date())
            .message(ex.getMessage())
            .details(request.getDescription(true))
            .build();
    return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
