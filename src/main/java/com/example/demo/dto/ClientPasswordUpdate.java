package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

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
    private String newPassword;
}
