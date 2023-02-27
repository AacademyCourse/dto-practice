package com.example.demo.convertor;

import com.example.demo.entity.Client;
import com.example.demo.entity.Currency;
import com.example.demo.entity.Transaction;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.Instant;
@Component
public class TransactionConvertor {


    public Transaction toTransaction(Client sender, Client reciever, Currency currency, BigDecimal amount,String reason){
        return Transaction.builder()
                .sender(sender)
                .receiver(reciever)
                .currency(currency)
                .reason(reason)
                .date(Instant.now())
                .amount(amount)
                .build();
    }
}
