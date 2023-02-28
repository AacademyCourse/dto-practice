package com.example.demo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class TransactionRequest {

    @NotNull
    @Email(message = "Email should have proper email format")
    private String sourceEmail;
    @NotNull
    @Email(message = "Email should have proper email format")
    private String destinationEmail;
    @NotNull
    private Double amount;
    @NotNull
    private CurrencyRequest currencyName;
    @NotNull
    private String reason;

}
