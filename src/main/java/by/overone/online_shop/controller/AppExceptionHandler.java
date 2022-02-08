package by.overone.online_shop.controller;

import by.overone.online_shop.controller.exception.ExceptionResponse;
import by.overone.online_shop.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.TypeMismatchException;
import org.springframework.context.MessageSource;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
@RequiredArgsConstructor
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

    private final MessageSource messageSource;


    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ExceptionResponse response = new ExceptionResponse();
        response.setException(ex.getClass().getSimpleName());
        response.setErrorCode("7");
        response.setMessage(messageSource.getMessage("7", null, request.getLocale()));
        log.error("BAD_REQUEST", ex);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
                                                                         HttpHeaders headers, HttpStatus status,
                                                                         WebRequest request) {
        ExceptionResponse response = new ExceptionResponse();
        response.setException(ex.getClass().getSimpleName());
        response.setErrorCode("4");
        response.setMessage(messageSource.getMessage("4", null, request.getLocale()));
        log.error("NOT_ALLOWED", ex);
        return new ResponseEntity<>(response, HttpStatus.METHOD_NOT_ALLOWED);
    }

//    @ExceptionHandler(Exception.class)
//    @RequestMapping(value = "/**")
//    public ResponseEntity<ExceptionResponse> hfjfkfkfk(){
//        ExceptionResponse response = new ExceptionResponse();
//        response.setErrorCode("404");
//        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
//    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ExceptionResponse> entityNotFoundHandler(EntityNotFoundException e, WebRequest request) {
        ExceptionResponse response = new ExceptionResponse();
        response.setException(e.getClass().getSimpleName());
        response.setErrorCode(e.getMessage());
        String message = "";
        switch (e.getMessage()) {
            case "1":
                message = messageSource.getMessage("1", null, request.getLocale());
                break;
            case "2":
                message = messageSource.getMessage("2", null, request.getLocale());
                break;
        }
        response.setMessage(message);
        log.error("Not found", e);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SQLException.class)
    public ResponseEntity<ExceptionResponse> sqlExceptionHandler(SQLException e, WebRequest request) {
        ExceptionResponse response = new ExceptionResponse();
        response.setException(e.getClass().getSimpleName());
        response.setErrorCode("3");
        response.setMessage(messageSource.getMessage("3", null, request.getLocale()));
        log.error("SQL exception", e);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public ResponseEntity<ExceptionResponse> entityAlreadyExistHandler(DuplicateKeyException e, WebRequest request) {
        ExceptionResponse response = new ExceptionResponse();
        response.setException(e.getClass().getSimpleName());
        response.setErrorCode("5");
        response.setMessage(messageSource.getMessage("5", null, request.getLocale()));
        log.error("ALREADY EXIST EXCEPTION: ", e);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        List<ExceptionResponse> responses = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> new ExceptionResponse(error.getField() + " " + error.getDefaultMessage(),
                        null, null)).collect(Collectors.toList());
        log.error("BAD_REQUEST", ex);
        return new ResponseEntity<>(responses, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers, HttpStatus status,
                                                                  WebRequest request) {
        ExceptionResponse response = new ExceptionResponse();
        response.setException(ex.getClass().getSimpleName());
        response.setErrorCode("6");
        response.setMessage(messageSource.getMessage("6", null, request.getLocale()));
        log.error("BAD_REQUEST", ex);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
