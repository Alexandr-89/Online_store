package by.overone.online_shop.controller;

import by.overone.online_shop.controller.exception.ExceptionResponse;
import by.overone.online_shop.exception.EntityNotFoundException;
import by.overone.online_shop.validator.exception.ValidatorException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.SQLException;
@Slf4j
@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
                                                                         HttpHeaders headers, HttpStatus status,
                                                                         WebRequest request) {
        return super.handleHttpRequestMethodNotSupported(ex, headers, status, request);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ExceptionResponse> entityNotFoundHandler(EntityNotFoundException e){
        ExceptionResponse response = new ExceptionResponse();
        response.setException(e.getClass().getSimpleName());
        response.setErrorCode(e.getMessage());
        String message = "";
        switch (e.getMessage()){
            case "1": message = "User not found" ;
            break;
            case "2": message = "Product not found" ;
            break;
        }
        response.setMessage(message);
        log.info("Not found", e);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SQLException.class)
    public ResponseEntity<ExceptionResponse> sqlExceptionHandler(SQLException e){
        ExceptionResponse response = new ExceptionResponse();
        response.setException(e.getClass().getSimpleName());
        response.setErrorCode("3");
        response.setMessage("SQL exception");
        log.info("SQL exception", e);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

//    @ExceptionHandler(DuplicateKeyException.class)
//    public ResponseEntity<ExceptionResponse> entityAlreadyExistHandler(DuplicateKeyException e){
//        ExceptionResponse response = new ExceptionResponse();
//        response.setException(e.getClass().getSimpleName());
//        response.setErrorCode("4");
//        response.setMessage("Entity already exist");
//        log.info("ALREADY EXIST EXCEPTION: ", e);
//        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
//    }
}
