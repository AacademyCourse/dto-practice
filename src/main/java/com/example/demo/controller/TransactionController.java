package com.example.demo.controller;

import com.example.demo.dto.TransactionRequest;
import com.example.demo.dto.TransactionResponse;
import com.example.demo.service.impl.TransactionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/transaction")
public class TransactionController {

    @Autowired
    private TransactionServiceImpl transactionService;
    @PostMapping (path = "/execute")
    public ResponseEntity<TransactionResponse> executeTransaction(@RequestBody TransactionRequest trnRequest) {
        return ResponseEntity.status(HttpStatus.OK).body((transactionService.performTransaction(trnRequest)));
    }
}
