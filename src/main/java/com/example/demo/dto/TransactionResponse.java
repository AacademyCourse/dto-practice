package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Component
public class TransactionResponse {
    private String message;
}
