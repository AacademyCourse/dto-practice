package com.example.demo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
