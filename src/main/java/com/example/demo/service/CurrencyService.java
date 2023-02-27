package com.example.demo.service;

import com.example.demo.entity.Currency;
import com.example.demo.exception.RecordNotFoundException;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Set;

public interface CurrencyService {


    Currency addCurrency(Currency currency);
    void  deleteCurrency(Long id) ;
    Currency findByName(String name) throws RecordNotFoundException;
    Currency findById(Long id) throws RecordNotFoundException;
    Set<Currency> findAll();
}

