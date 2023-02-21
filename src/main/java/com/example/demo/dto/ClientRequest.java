package com.example.demo.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ClientRequest {
    @NotNull
    @Size(min = 3, message = "First name should be min 3 characters!")
    private String firstName;
    @NotNull
    @Size(min = 3, message = "Last name should be min 3 characters!")
    private String lastName;

    @Size(min = 5, message = "Address should be min 5 characters!")
    private String address;
    @NotNull
    @Size(min = 10, max = 10, message = "IBAN should be 10 characters!")
    private String iban;
}
