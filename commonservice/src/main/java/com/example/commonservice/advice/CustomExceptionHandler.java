package com.example.commonservice.advice;

import com.example.commonservice.exceptions.ResourceConflictException;
import com.example.commonservice.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;

@RestControllerAdvice
@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE + 1)
public class CustomExceptionHandler {

  @ExceptionHandler(value = ResourceNotFoundException.class)
  @ResponseStatus(code = HttpStatus.NOT_FOUND)
  public ResponseEntity<Object> handleResourceNotFoundException(
      ResourceNotFoundException ex, HttpServletRequest request) {
    log.error("handleResourceNotFoundException: ",ex);
    var response = new ExceptionApiResponse();

    response.setCode(HttpStatus.NOT_FOUND.value());
    response.setTrace(Arrays.asList(ex.getStackTrace()).toString());
    response.setMessage(ex.getMessage());
    response.setHttpStatus(HttpStatus.NOT_FOUND);
    response.setPath(request.getContextPath() + request.getServletPath());
    return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(value = MethodArgumentNotValidException.class)
  @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
  public ResponseEntity<Object> handleException(MethodArgumentNotValidException ex, HttpServletRequest request) {
    log.error("handleException: ",ex);
    var response = new ExceptionApiResponse();

    response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
    response.setTrace(Arrays.asList(ex.getStackTrace()).toString());
    response.setMessage(ex.getMessage());
    response.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
    response.setPath(request.getContextPath() + request.getServletPath());
    return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(value = ResourceConflictException.class)
  @ResponseStatus()
  public ResponseEntity<Object> handleConflictException(ResourceConflictException ex, HttpServletRequest request) {
    log.error("handleConflictException: ",ex);
    var response = new ExceptionApiResponse();

    response.setCode(HttpStatus.CONFLICT.value());
    response.setTrace(Arrays.asList(ex.getStackTrace()).toString());
    response.setMessage(ex.getMessage());
    response.setHttpStatus(HttpStatus.CONFLICT);
    response.setPath(request.getContextPath() + request.getServletPath());
    return new ResponseEntity<>(response, HttpStatus.CONFLICT);
  }
}
