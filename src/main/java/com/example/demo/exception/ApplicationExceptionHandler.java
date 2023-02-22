package com.example.demo.exception;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;

@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    String handleSQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException ex){
        return ex.getMessage();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(RecordNotFoundException.class)
    String handlerRecordNotFoundException(RecordNotFoundException ex){
        return ex.getMessage();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EmptyResultDataAccessException.class)
    String handlerEmptyResultDataAccessException(EmptyResultDataAccessException ex){
        return "Record not found";
    }





}
