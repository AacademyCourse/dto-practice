package com.example.demo.convertor;

import com.example.demo.dto.CurrencyRequest;
import com.example.demo.dto.CurrencyResponse;
import com.example.demo.entity.Currency;
import org.springframework.stereotype.Component;

@Component
public class CurrencyConvertor {
    public Currency convertToCurrency(CurrencyRequest currencyRequest){
        return Currency.builder()
                .currencyCode(currencyRequest.getCurrencyName())
                .build();
    }

    public CurrencyResponse convertToCurrencyResponse(Currency currency){
        return CurrencyResponse.builder()
                .id(currency.getId().toString())
                .currencyName(currency.getCurrencyCode())
                .build();
    }
}
