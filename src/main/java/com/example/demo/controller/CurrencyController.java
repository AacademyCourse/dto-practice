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
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/currency")
public class CurrencyController {

    @Autowired
    CurrencyServiceImpl currencyServiceImpl;

    @Autowired
    CurrencyConvertor currencyConvertor;

    @PostMapping
    ResponseEntity<CurrencyResponse> save(@Valid @RequestBody CurrencyRequest currencyRequest) throws SQLIntegrityConstraintViolationException {
        Currency currency = currencyConvertor.convertToCurrency(currencyRequest);
        Currency currencySaved = currencyServiceImpl.addCurrency(currency);
        CurrencyResponse currencyResponse = currencyConvertor.convertToCurrencyResponse(currencySaved);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(currencyResponse);
    }

    @DeleteMapping(path = "/{id}")
    ResponseEntity<String> delete(@PathVariable Long id) {
        currencyServiceImpl.deleteCurrency(id);
        return ResponseEntity
                .ok()
                .body(Long.toString(id) + " deleted");
    }

    @GetMapping(path = "/{id}")
    ResponseEntity<CurrencyResponse> getById(@PathVariable Long id){
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(currencyConvertor.convertToCurrencyResponse(currencyServiceImpl.findById(id)));
    }


   @GetMapping(path = "/code/{currencyCode}")
   ResponseEntity<CurrencyResponse> getByName(@PathVariable String currencyCode){
       return ResponseEntity
               .status(HttpStatus.FOUND)
               .body(currencyConvertor.convertToCurrencyResponse(currencyServiceImpl.findByName(currencyCode)));

   }

    @GetMapping(path ="/all")
    ResponseEntity<Set<CurrencyResponse>> getAll(){
        Set<CurrencyResponse> currenciesResponses = currencyServiceImpl.findAll()
                .stream()
                .map(currencyConvertor :: convertToCurrencyResponse)
                .collect(Collectors.toSet());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(currenciesResponses);
    }

}
