package com.example.demo.controller;

import com.example.demo.dto.TransactionRequest;
import com.example.demo.dto.TransactionResponse;
import com.example.demo.exception.RecordNotFoundException;
import com.example.demo.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path= "/transaction")
public class TransactionController {
    @Autowired
    TransactionService transactionService;

    @PostMapping(path = "/execute")
    ResponseEntity<TransactionResponse> executeTransaction(@RequestBody TransactionRequest trnRequest) throws RecordNotFoundException {
       TransactionResponse trnResponse = transactionService.performTransaction(trnRequest);

        return ResponseEntity.ok(trnResponse);
    }
}
