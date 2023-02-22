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

    private  CurrencyRepository currencyRepository;

    public CurrencyServiceImpl(CurrencyRepository currencyRepository, CurrencyRepository currencyRepository1) {
        this.currencyRepository = currencyRepository1;
    }

    @Override
    public Currency addCurrency(Currency currency) throws SQLIntegrityConstraintViolationException  {
        return  currencyRepository.save(currency);
    }


    @Override
    public void deleteCurrency(Long id) throws EmptyResultDataAccessException {
            try {currencyRepository.deleteById(id);
            } catch (EmptyResultDataAccessException ex) {};
    }

    @Override
    public Currency findByName(String currencyCode) throws RecordNotFoundException {
        Currency currency = currencyRepository.findByCurrencyCode(currencyCode);
        if(currency == null){
            throw new RecordNotFoundException(String.format("Currency %s not found", currencyCode));
        }
        return currency;

    }

    @Override
    public Currency findById(Long id) {
           //return currencyRepository.findById(id).orElseThrow(RuntimeException::new);
        Optional<Currency> currency = currencyRepository.findById(id);
        if (currency.isPresent()) {
            return currency.get();
        } else {
            throw new RecordNotFoundException(String.format("Currency with id %s not found", id));
        }
    }

    @Override
    public Set<Currency> findAll() {
        return new HashSet<>(currencyRepository.findAll());
    }

}
