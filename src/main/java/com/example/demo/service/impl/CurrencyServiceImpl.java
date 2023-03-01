package com.example.demo.service.impl;


import com.example.demo.entity.Currency;
import com.example.demo.exception.RecordNotFoundException;
import com.example.demo.repository.ClientRepository;
import com.example.demo.repository.CurrencyRepository;
import com.example.demo.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
public class CurrencyServiceImpl implements CurrencyService {

    private final CurrencyRepository currencyRepository;

    @Autowired
    public CurrencyServiceImpl(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    @Override
    public Currency addCurrency(Currency currency) {
        return currencyRepository.save(currency);
    }

    @Override
    public void deleteCurrency(Long id) {
        currencyRepository.deleteById(id);
    }

    @Override
    public Currency findByName(String currency) {
        return currencyRepository.findByCurrencyCode(currency)
                .orElseThrow(() -> new RecordNotFoundException(String.format("Currency with id %s not found", id)));
    }

    @Override
    public Currency findById(Long id) {
        return currencyRepository.findById(id).orElseThrow(()
                -> new RecordNotFoundException(String.format("Currency with id %s not found" + id)));
    }

    @Override
    public Set<Currency> findAll() {
        return new HashSet<Currency>(currencyRepository.findAll());
    }
}
