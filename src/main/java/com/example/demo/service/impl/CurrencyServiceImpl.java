package com.example.demo.service.impl;

import com.example.demo.entity.Currency;
import com.example.demo.repository.CurrencyRepository;
import com.example.demo.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class CurrencyServiceImpl implements CurrencyService {
    private final CurrencyRepository currencyRepository;

    @Autowired
    public CurrencyServiceImpl(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    @Override
    public Currency addCurrency(Currency currency) {
        Currency newCurrency = new Currency();
        newCurrency.setCurrencyCode(currency.getCurrencyCode());
        return currencyRepository.save(currency);
    }

    @Override
    public void deleteCurrency(Long id) {
        currencyRepository.deleteById(id);
    }

    @Override
    public Currency findCurrencyByCurrencyCode(String currencyCode) {
        return currencyRepository.findByCurrencyCode(currencyCode);
    }

    @Override
    public Currency findCurrencyById(Long id) {
        Currency searchedCurrency = new Currency();
        if (currencyRepository.findById(id).isPresent()) {
            searchedCurrency = currencyRepository.findById(id).get();
        }
        return searchedCurrency;
    }

    @Override
    public Set<Currency> findAll() {
        return new HashSet<>(currencyRepository.findAll());
    }
}
