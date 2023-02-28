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
    @Email
    private String sourceEmail;
    @NotNull
    @Email
    private String destinationEmail;
    @NotNull
    @Size(min = 2)
    private Double amount;
    @NotNull
    @Size(min = 2, max = 4)
    private CurrencyRequest currency;
    @Size(min = 4)
    private String reason;
}
