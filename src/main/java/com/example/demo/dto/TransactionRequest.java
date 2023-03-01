package com.example.demo.dto;

import jakarta.validation.constraints.Email;
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
public class TransactionRequest {
    @NotNull
    @Email(message = "Email should have proper email format!")
    private String sourceEmail;
    @NotNull
    @Email(message = "Email should have proper email format!")
    private String destinationEmail;
    @NotNull
    @Size(min = 2, message = "Amount field should be min with 2 characters!")
    private Double amount;
    @NotNull
    @Size(message = "Currency should be from 2 to 4 characters!", min = 2, max = 4)
    private CurrencyRequest currency;
    @Size(min = 4, message = "Reason field should be min with 5 characters!")
    private String reason;
}
