package com.example.demo.controller;

import com.example.demo.convertor.CurrencyConvertor;
import com.example.demo.dto.CurrencyRequest;
import com.example.demo.dto.CurrencyResponse;
import com.example.demo.entity.Currency;
import com.example.demo.service.CurrencyService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/currency")
public class CurrencyController {

    @Autowired
    CurrencyService currencyService;

    @Autowired
    CurrencyConvertor currencyConvertor;

    @PostMapping
    ResponseEntity<CurrencyResponse> save(@Valid @RequestBody CurrencyRequest currencyRequest){
        Currency currency = currencyConvertor.convertToCurrency(currencyRequest);
        Currency currencySaved = currencyService.addCurrency(currency);
        CurrencyResponse currencyResponse = currencyConvertor.convertToCurrencyResponse(currencySaved);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(currencyResponse);
    }

    @GetMapping(path = "/{id}")
    ResponseEntity<CurrencyResponse> getById(@PathVariable Long id){
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(currencyConvertor.convertToCurrencyResponse(currencyService.findById(id)));
    }

    @GetMapping(path ="/all")
    ResponseEntity<Set<CurrencyResponse>> getAll(){
        Set<CurrencyResponse> currenciesResponses = currencyService.findAll()
                .stream()
                .map(currencyConvertor :: convertToCurrencyResponse)
                .collect(Collectors.toSet());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(currenciesResponses);
    }

    @DeleteMapping(path = "/{id}")
    ResponseEntity<String> delete(@PathVariable Long id){
        currencyService.deleteCurrency(id);
        return ResponseEntity
                .ok()
                .body(Long.toString(id) + " deleted");
    }
}
