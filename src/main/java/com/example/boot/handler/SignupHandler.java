package com.example.boot.handler;

import com.example.boot.exception.UsernameExist;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class SignupHandler {
    @ExceptionHandler(UsernameExist.class)
    public ResponseEntity<?> handlerUsernameExist (Exception ex, WebRequest request) {
        return new ResponseEntity<>(new ErrorHandler(600, "User name exits", ex.getMessage()), HttpStatus.OK);
    }
}
