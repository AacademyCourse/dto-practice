package com.example.demo.dto;

import com.example.demo.entity.Currency;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;
import org.springframework.stereotype.Component;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Component
public class TransactionRequest {
    @Email
    private String sourceEmail;
    private String destinationEmail;
    private Double amount;
    private CurrencyRequest currency;
    private String reason;
}
