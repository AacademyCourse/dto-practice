package com.example.demo.service.impl;

import com.example.demo.entity.Currency;
import com.example.demo.exception.RecordNotFoundException;
import com.example.demo.repository.CurrencyRepository;
import com.example.demo.service.CurrencyService;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class CurrencyServiceImpl implements CurrencyService {
    private final CurrencyRepository currencyRepository;

    public CurrencyServiceImpl(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    @Override
    public Currency addCurrency(Currency currency){
        return  currencyRepository.save(currency);
    }

    @Override
    public void deleteCurrency(Long id){
            currencyRepository.deleteById(id);
    }

    @Override
    public Currency findById(Long id) {
        //return currencyRepository.findById(id).orElseThrow(RuntimeException::new);
        return currencyRepository.findById(id)
                .orElseThrow(()-> new RecordNotFoundException(String.format("Currency with id %s not found", id)));
    }

    @Override
    public Currency findByName(String currencyCode){
        return currencyRepository.findByCurrencyCode(currencyCode)
                .orElseThrow(()-> new RecordNotFoundException(String.format("Currency %s not found", currencyCode)));
    }

    @Override
    public Set<Currency> findAll() {
        return new HashSet<>(currencyRepository.findAll());
    }

}
