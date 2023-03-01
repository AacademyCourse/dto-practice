package com.example.demo.convertor;

import com.example.demo.dto.CurrencyRequest;
import com.example.demo.dto.CurrencyResponse;
import com.example.demo.entity.Currency;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
@Getter
@Setter
public class CurrencyConvertor {

    public Currency convertCurrency (CurrencyRequest currencyRequest) {
        return Currency.builder()
                .currencyCode(currencyRequest.getCurrencyName())
                .build();
    }

    public CurrencyResponse toCurrencyResponse (Currency currencyCoder) {
        return CurrencyResponse.builder()
                .currencyName(currencyCoder.getCurrencyCode())
                .build();
    }
}
