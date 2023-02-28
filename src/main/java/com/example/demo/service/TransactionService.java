package com.example.demo.service;

import com.example.demo.dto.*;
import com.example.demo.entity.Transaction;
import java.util.Set;

public interface TransactionService {

    TransactionResponse performTransaction(TransactionRequest transaction);
    Set<Transaction> findAll();
    String deposit(TransactionDeposit transactionDeposit);
}
