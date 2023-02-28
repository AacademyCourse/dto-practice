package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;



public class RecordNotFoundException extends RuntimeException {
    //must be RuntimeException, otherwise .otElseThroe is not working
    public RecordNotFoundException(String message) {
        super(message);
    }
}
