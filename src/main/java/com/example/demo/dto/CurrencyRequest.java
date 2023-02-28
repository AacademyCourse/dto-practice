package com.example.demo.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CurrencyRequest {

    @NotNull
    @Size(min = 2, max=4, message = "min = 2, max=4,")
    String currencyName;
}
