package com.example.demo.service.impl;

import com.example.demo.convertor.TransactionConvertor;
import com.example.demo.dto.TransactionDeposit;
import com.example.demo.dto.TransactionRequest;
import com.example.demo.dto.TransactionResponse;
import com.example.demo.entity.Client;
import com.example.demo.entity.Currency;
import com.example.demo.entity.Transaction;
import com.example.demo.repository.TransactionRepository;
import com.example.demo.service.ClientService;
import com.example.demo.service.CurrencyService;
import com.example.demo.service.TransactionService;
import com.example.demo.util.trnUtil;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.Set;

import static java.math.BigDecimal.valueOf;


@Service
@AllArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private final ClientService clientService;
    @Autowired
    private final CurrencyService currencyService;
    @Autowired
    private final TransactionRepository transactionRepository;
    @Autowired
    private final TransactionConvertor convertor;

    @Override
    public TransactionResponse performTransaction(TransactionRequest transaction) {
        Client sender = clientService.findByEmail("lilly@gmail.com");
        Client receiver = clientService.findByEmail("billy@gmail.com");
        if (trnUtil.compareAmounts(sender.getBalance(), transaction.getAmount())) {
            sender.setBalance(valueOf(sender.getBalance().doubleValue() - transaction.getAmount()));
            receiver.setBalance(valueOf(receiver.getBalance().doubleValue() + transaction.getAmount()));
        }
        Currency trnCurrency = currencyService.findByName(transaction.getCurrencyName().getCurrencyName());
        Transaction trn = convertor.toTransaction(sender, receiver, trnCurrency,
                valueOf(transaction.getAmount()), transaction.getReason());
        Transaction savedTransaction = transactionRepository.save(trn);
        System.out.print("sender new balance: " + sender.getBalance());
        System.out.print("receiver new balance: " + receiver.getBalance());
        return new TransactionResponse(String.format("Transaction to %s was executed. ",
                receiver.getEmail()) + receiver.getBalance());
    }

    @Override
    public Set<Transaction> findAll() {
        return new HashSet<>(transactionRepository.findAll());
    }

    @Override
    public String deposit(TransactionDeposit transactionDeposit) {
        Client client = clientService.findByEmail(transactionDeposit.getEmail()); //--> Client not found
        client.setBalance(client.getBalance().add(transactionDeposit.getAmount()));
        return (String.format("Deposit to %s was executed.", client.getEmail()));
    }
}


