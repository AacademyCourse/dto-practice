package com.example.demo.convertor;

import com.example.demo.dto.TransactionResponse;
import com.example.demo.entity.Client;
import com.example.demo.entity.Currency;
import com.example.demo.entity.Transaction;
import com.example.demo.repository.TransactionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import java.time.Instant;

@Component
public class TransactionConvertor {

    public Transaction toTransaction(Client sender, Client receiver, Currency currency, BigDecimal amount, String reason){
        return Transaction.builder()
                .sender(sender)
                .receiver(receiver)
                .currency(currency)
                .reason(reason)
                .date(Instant.now())
                .amount(amount)
                .build();
    }

    public TransactionResponse toResponse(Transaction transaction){
        return TransactionResponse.builder()
                .message(transaction.getSender().getEmail()+ " --> "  +transaction.getSender().getEmail() + ": " + transaction.getAmount())
                .build();
    }


}
