package com.example.demo.service;


import com.example.demo.dto.TransactionRequest;
import com.example.demo.dto.TransactionResponse;
import com.example.demo.exception.RecordNotFoundException;

import java.util.Set;


public interface TransactionService {

    TransactionResponse performTransaction(TransactionRequest trnRequest) throws RecordNotFoundException;
}
