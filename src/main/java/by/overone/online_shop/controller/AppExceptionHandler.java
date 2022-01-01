package by.overone.online_shop.controller;

import by.overone.online_shop.controller.exception.ExceptionResponse;
import by.overone.online_shop.validator.exception.ValidatorException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler(ValidatorException.class)
    public ResponseEntity<ExceptionResponse> validationHandar(ValidatorException e){
        ExceptionResponse exceptionResponse = new ExceptionResponse(e.getMessage());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
}
