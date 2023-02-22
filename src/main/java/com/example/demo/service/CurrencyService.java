package com.example.demo.service;

import com.example.demo.entity.Currency;
import com.example.demo.exception.RecordNotFoundException;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Set;

public interface CurrencyService {


    Currency addCurrency(Currency currency) throws SQLIntegrityConstraintViolationException;
    void  deleteCurrency(Long id) throws RecordNotFoundException;
    Currency findByName(String name) throws RecordNotFoundException;
    Currency findById(Long id)throws RecordNotFoundException;
    Set<Currency> findAll();
}

