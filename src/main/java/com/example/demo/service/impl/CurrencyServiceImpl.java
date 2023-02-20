package com.example.demo.service.impl;

import com.example.demo.entity.Currency;
import com.example.demo.repository.CurrencyRepository;
import com.example.demo.service.CurrencyService;
import org.springframework.stereotype.Service;

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
    public Currency addCurrency(Currency currency) {
        return currencyRepository.save(currency);
    }


    @Override
    public void deleteCurrency(Long id) {
            currencyRepository.deleteById(id);
    }

    @Override
    public Currency findByName(String name) {
        return currencyRepository.findByCurrencyCode(name);
    }

    @Override
    public Currency findById(Long id) {
        //   return currencyRepository.findById(id).orElseThrow(RuntimeException::new);
        Optional<Currency> currency = currencyRepository.findById(id);
        if (currency.isPresent()) {
            return currency.get();
        } else {
            throw new RuntimeException();
        }
    }

    @Override
    public Set<Currency> findAll() {
        return new HashSet<>(currencyRepository.findAll());
    }

}
