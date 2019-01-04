package com.example.jenkins.errorHandler;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class ErrorMessageDetails {
    private Date timestamp;
    private String message;
    private String details;
}
