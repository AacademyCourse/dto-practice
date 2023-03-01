package com.example.demo.service.impl;

import com.example.demo.convertor.TransactionConvertor;
import com.example.demo.dto.TransactionRequest;
import com.example.demo.dto.TransactionResponse;
import com.example.demo.entity.Client;
import com.example.demo.entity.Currency;
import com.example.demo.entity.Transaction;
import com.example.demo.exception.RecordNotFoundException;
import com.example.demo.repository.TransactionRepository;
import com.example.demo.service.ClientService;
import com.example.demo.service.TransactionService;
import com.example.demo.util.TrnUtil;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final ClientService clientService;
    private final TransactionConvertor convertor;
    private final CurrencyServiceImpl currencyService;
    private final TransactionRepository transactionRepository;

    public TransactionServiceImpl (ClientService clientService, TransactionConvertor convertor, CurrencyServiceImpl currencyService, TransactionRepository transactionRepository ) {
        this.clientService = clientService;
        this.convertor = convertor;
        this.currencyService = currencyService;
        this.transactionRepository = transactionRepository;
    }


    @Transactional
    @Override
    public TransactionResponse performTransaction(TransactionRequest trnRequest) throws RecordNotFoundException {
        Client sender = clientService.findByEmail(trnRequest.getSourceEmail());
        Client receiver = clientService.findByEmail(trnRequest.getDestinationEmail());

        if (TrnUtil.compareAmounts(sender.getBalance(), trnRequest.getAmount())) {
            sender.setBalance(BigDecimal.valueOf(sender.getBalance().doubleValue() - trnRequest.getAmount()));
            receiver.setBalance(BigDecimal.valueOf(receiver.getBalance().doubleValue() + trnRequest.getAmount()));
        }
        Currency trnCurrency = currencyService.findByName(trnRequest.getCurrency().getCurrencyName());
        Transaction trn = convertor.toTransaction(sender, receiver, trnCurrency, new BigDecimal(trnRequest.getAmount()), trnRequest.getReason());
        transactionRepository.save(trn);
        return new TransactionResponse(String.format("Transaction to %s was executed successfully", receiver.getEmail()));

    }
}
