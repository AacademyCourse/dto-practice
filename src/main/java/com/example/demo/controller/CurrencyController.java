package com.example.demo.controller;


import com.example.demo.convertor.CurrencyConvertor;
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
@RequestMapping(path = "/currencies")
public class CurrencyController {
    @Autowired
    CurrencyServiceImpl currencyService;
    @Autowired
    CurrencyConvertor currencyConvertor;

    @PostMapping
    ResponseEntity<CurrencyResponse> save(@RequestBody @Valid CurrencyRequest currencyRequest) {
        com.example.demo.entity.Currency savedCurrency = currencyService.addCurrency(
                currencyConvertor.convertCurrency(currencyRequest));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(currencyConvertor.toCurrencyResponse(savedCurrency));
    }

    @GetMapping(path = "/all")
    ResponseEntity<Set<CurrencyResponse>> getAll() {
        Set<CurrencyResponse> currResp = new HashSet<>();
        Set<com.example.demo.entity.Currency> currencies = currencyService.findAll();

        for (Currency currency : currencies) {
            currResp.add(currencyConvertor.toCurrencyResponse(currency));
        }
        return ResponseEntity.status(HttpStatus.OK).body(currResp);
    }

    @GetMapping(path = "/{id}")
    ResponseEntity<CurrencyResponse> getCurrency(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(currencyConvertor.toCurrencyResponse(currencyService.findById(id)));
    }

    @DeleteMapping(path = "/{id}")
    ResponseEntity<String> delete(@PathVariable Long id) {
        currencyService.deleteCurrency(id);
        return ResponseEntity.status(HttpStatus.OK).body("Currency deleted");
    }

    @GetMapping(path = "/code/{currencyCode}")
    ResponseEntity<CurrencyResponse> getByName(@PathVariable String currencyCode){
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(currencyConvertor.toCurrencyResponse(currencyService.findByName(currencyCode)));
    }


}
