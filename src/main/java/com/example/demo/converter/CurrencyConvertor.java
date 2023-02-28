package com.example.demo.converter;

import com.example.demo.dto.CurrencyRequest;
import com.example.demo.dto.CurrencyResponse;
import com.example.demo.entity.Currency;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor

public class CurrencyConvertor {
    public Currency convertToCurrency(CurrencyRequest currencyRequest) {
        return Currency.builder()
                .currencyCode(currencyRequest.getCurrencyName())
                .build();
    }

    public CurrencyResponse toCurrencyResponse(Currency currency) {
        return CurrencyResponse.builder()
                .currencyName(currency.getCurrencyCode())
                .build();
    }
}
