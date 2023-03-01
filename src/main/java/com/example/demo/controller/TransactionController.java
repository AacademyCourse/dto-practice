package com.example.demo.controller;

import com.example.demo.dto.TransactionRequest;
import com.example.demo.dto.TransactionResponse;
import com.example.demo.exception.RecordNotFoundException;
import com.example.demo.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/transactions")
public class TransactionController {
    @Autowired
    TransactionService transactionService;

    @PostMapping(path = "run")
    ResponseEntity<TransactionResponse> runTransaction (@RequestBody TransactionRequest transactionRequest) throws RecordNotFoundException {
        TransactionResponse transactionResponse = transactionService.performTransaction(transactionRequest);

        return ResponseEntity.ok(transactionResponse);

    }
}
