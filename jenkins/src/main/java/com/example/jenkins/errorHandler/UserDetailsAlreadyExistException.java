package com.example.jenkins.errorHandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class UserDetailsAlreadyExistException extends RuntimeException {
    public UserDetailsAlreadyExistException(String message) {
        super(message);
    }
}
