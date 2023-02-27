package com.example.demo.converter;

import com.example.demo.entity.Client;
import com.example.demo.entity.Currency;
import com.example.demo.entity.Transaction;

import java.math.BigDecimal;

public class TransactionConverter {

    public Transaction toTransaction(Client sender, Client receiver, Currency currency, BigDecimal amount, String reason) {
        return Transaction.builder().sender(sender)
                .receiver(receiver)
                .currency(currency)
                .amount(amount)
                .reason(reason)
                .build();
    }
}
