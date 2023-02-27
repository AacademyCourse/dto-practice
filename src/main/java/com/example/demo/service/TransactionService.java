package com.example.demo.service;


import com.example.demo.dto.TransactionRequest;
import com.example.demo.dto.TransactionResponse;

public interface TransactionService {
    TransactionResponse performTransaction(TransactionRequest trnRequest);
}
