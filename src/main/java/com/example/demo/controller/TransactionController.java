package com.example.demo.controller;

import com.example.demo.convertor.TransactionConvertor;
import com.example.demo.dto.StatusResponse;
import com.example.demo.dto.TransactionDeposit;
import com.example.demo.dto.TransactionRequest;
import com.example.demo.dto.TransactionResponse;
import com.example.demo.entity.Transaction;
import com.example.demo.service.TransactionService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Set;
import java.util.stream.Collectors;


@RestController
@RequestMapping(path = "/transaction")
public class TransactionController {

    @Autowired
    TransactionService service;
    @Autowired
    TransactionConvertor convertor;

    @Transactional
    @PostMapping(path = "/execute")
    ResponseEntity<TransactionResponse> execute (@Valid @RequestBody  TransactionRequest transactionRequest){
        return ResponseEntity
                .ok()
                .body(service.performTransaction(transactionRequest));
    }

    /*@Email @NotNull with @Valid not working*/
    @Transactional
    @PostMapping(path = "/deposit")
    ResponseEntity<String> deposit (@Valid @RequestBody TransactionDeposit transactionDeposit){
        return ResponseEntity
                .ok()
                .body(service.deposit(transactionDeposit));
    }

    @GetMapping(path = "/all")
    ResponseEntity<Set<TransactionResponse>> findAll(){
       Set<TransactionResponse> transactions = service.findAll()
                .stream()
                .map(convertor :: toResponse)
                .collect(Collectors.toSet());
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(transactions);
    }

}
