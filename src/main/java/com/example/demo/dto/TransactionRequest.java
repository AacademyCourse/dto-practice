package com.example.demo.dto;

import lombok.*;
import org.hibernate.validator.constraints.Email;
import org.springframework.stereotype.Component;
@Component
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class TransactionRequest {
    @Email
    private String sourceEmail;
    private String destinationEmail;
    private Double amount;
    private CurrencyRequest currency;
    private String reason;
}
