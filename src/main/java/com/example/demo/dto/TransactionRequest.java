package com.example.demo.dto;

import lombok.*;
import org.springframework.stereotype.Component;


@Component
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class TransactionRequest {
    private String sourceEmail;
    private String destinationEmail;
    private Double amount;
    private CurrencyRequest currency;
    private String reason;
}
