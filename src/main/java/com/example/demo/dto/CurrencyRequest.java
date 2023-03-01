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
    @Size(min=2,max=4,message = "Currency name should be from 2 to 4 characters")
    private String currencyName;



}
