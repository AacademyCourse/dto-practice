package com.example.demo.service;

import com.example.demo.dto.TransactionRequest;
import com.example.demo.dto.TransactionResponse;
import com.example.demo.entity.Transaction;
import com.example.demo.exception.RecordNotFoundException;

public interface TransactionService {
    TransactionResponse performTransaction(TransactionRequest trnRequest) throws RecordNotFoundException;
}
