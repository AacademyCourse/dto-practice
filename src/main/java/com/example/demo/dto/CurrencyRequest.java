package com.example.demo.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class CurrencyRequest {
    @NotNull
    @Size (max = 4, min = 2, message = "Currency name should be from 2 to 4 characters!")
    private String currencyName;
}
