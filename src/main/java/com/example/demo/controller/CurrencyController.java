package com.example.demo.controller;

import com.example.demo.converter.CurrencyConvertor;
import com.example.demo.dto.CurrencyRequest;
import com.example.demo.dto.CurrencyResponse;
import com.example.demo.entity.Currency;
import com.example.demo.service.impl.CurrencyServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping (path = "/currency")
public class CurrencyController {
    @Autowired
    private CurrencyServiceImpl currencyService;
    @Autowired
    private CurrencyConvertor convertor;

    @GetMapping (path = "/all")
    public ResponseEntity<Set<CurrencyResponse>> getAll() {
        Set<CurrencyResponse> currResp = new HashSet<>();
        Set<Currency> currencies = currencyService.findAll();
        currencies.forEach(
                currency -> currResp.add(convertor.toCurrencyResponse(currency))
        );
        return ResponseEntity.status(HttpStatus.OK).body(currResp);
    }

    @GetMapping (path = "/{id}")
    public ResponseEntity<CurrencyResponse> getCurrency(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(convertor.toCurrencyResponse(currencyService.findCurrencyById(id)));
    }
    @PostMapping (path = "/add")
    public ResponseEntity<CurrencyResponse> save(@RequestBody @Valid CurrencyRequest currency) {
        Currency saved = currencyService.addCurrency(
                convertor.convertToCurrency(currency));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(convertor.toCurrencyResponse(saved));
    }

    @DeleteMapping (path = "/{id}/delete")
    public ResponseEntity<String> deleteById (@PathVariable Long id) {
        currencyService.deleteCurrency(id);
        return ResponseEntity.status(HttpStatus.OK).body("Currency deleted");
    }
}
