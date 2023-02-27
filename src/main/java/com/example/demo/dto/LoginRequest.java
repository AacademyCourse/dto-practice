package com.example.demo.dto;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import lombok.Setter;
import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
    @NotNull
    @Email
    String email;
    @NotNull
    String password;
}
