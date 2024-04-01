package com.avent.base.handler;

import com.avent.base.model.response.ResponseModel;
import com.avent.exception.EntityNotFoundException;
import com.avent.exception.InvalidEnumException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestValueException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ResponseModel<String>> handleException(HttpServletRequest httpServletRequest, EntityNotFoundException ex) {
        log.error("GlobalExceptionHandler: EntityNotFoundException handled:{} - {}", ex.getClass(), ex.getMessage());
        return new ResponseEntity<>(ResponseModel.fail(ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidEnumException.class)
    public ResponseEntity<ResponseModel<String>> handleException(HttpServletRequest httpServletRequest, InvalidEnumException ex) {
        log.error("GlobalExceptionHandler: InvalidEnumException handled:{} - {}", ex.getClass(), ex.getMessage());
        return new ResponseEntity<>(ResponseModel.fail(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MissingRequestValueException.class)
    public ResponseEntity<ResponseModel<String>> handleException(HttpServletRequest httpServletRequest, MissingRequestValueException ex) {
        log.error("GlobalExceptionHandler: MissingRequestValueException handled:{} - {}", ex.getClass(), ex.getMessage());
        return new ResponseEntity<>(ResponseModel.fail(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageConversionException.class)
    public ResponseEntity<ResponseModel<String>> handleException(HttpServletRequest httpServletRequest, HttpMessageConversionException ex) {
        log.error("GlobalExceptionHandler: HttpMessageConversionException handled:{} - {}", ex.getClass(), ex.getMessage());
        return new ResponseEntity<>(ResponseModel.fail(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseModel<String>> handleException(HttpServletRequest httpServletRequest, MethodArgumentNotValidException ex) {
        log.error("GlobalExceptionHandler: HttpMessageConversionException handled:{} - {}", ex.getClass(), ex.getMessage());
        return new ResponseEntity<>(ResponseModel.fail(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseModel<String>> handleException(HttpServletRequest httpServletRequest, Exception ex) {
        log.error("GlobalExceptionHandler: Exception handled:{} - {}", ex.getClass(), ex.getMessage());
        ex.printStackTrace();
        return new ResponseEntity<>(ResponseModel.fail(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
