package com.example.demo.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CurrencyRequest {

    @NotNull
    @Size(min = 2, max=4, message = "min = 2, max=4,")
    String currencyName;
}
