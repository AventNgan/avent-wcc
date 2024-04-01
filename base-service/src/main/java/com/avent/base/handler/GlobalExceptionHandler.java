package com.avent.base.handler;

import com.avent.base.model.response.ResponseModel;
import com.avent.exception.EntityNotFoundException;
import com.avent.exception.InvalidEnumException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ResponseModel<String>> handleException(HttpServletRequest httpServletRequest, EntityNotFoundException ex) {
        log.error("GlobalExceptionHandler: Exception handled:{} - {}", ex.getClass(), ex.getMessage());
        return new ResponseEntity<>(ResponseModel.fail(ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidEnumException.class)
    public ResponseEntity<ResponseModel<String>> handleException(HttpServletRequest httpServletRequest, InvalidEnumException ex) {
        log.error("GlobalExceptionHandler: Exception handled:{} - {}", ex.getClass(), ex.getMessage());
        return new ResponseEntity<>(ResponseModel.fail(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseModel<String>> handleException(HttpServletRequest httpServletRequest, Exception ex) {
        log.error("GlobalExceptionHandler: Exception handled:{} - {}", ex.getClass(), ex.getMessage());
        ex.printStackTrace();
        return new ResponseEntity<>(ResponseModel.fail(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
