package com.example.demo.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ClientPasswordUpdate {

    @NotNull
    private Long id;

    @NotNull
    private String password;

    @NotNull
    private String createPassword;

}
