package com.example.demo.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

import lombok.Setter;
import org.springframework.stereotype.Component;
@Component
@Getter
@Setter
public class LoginRequest {
    @NotNull
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+.[a-z]{2,3}",
            flags = Pattern.Flag.CASE_INSENSITIVE)
    String email;
    @NotNull
    String password;
}
