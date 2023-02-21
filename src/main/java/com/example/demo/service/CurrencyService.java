package com.example.demo.service;

import com.example.demo.entity.Currency;

import java.util.Set;

public interface CurrencyService {
    Currency addCurrency(Currency currency);
    void deleteCurrency(Long id);
    Currency findCurrencyByCurrencyCode(String currencyCode);
    Currency findCurrencyById(Long id);
    Set<Currency> findAll();
}
