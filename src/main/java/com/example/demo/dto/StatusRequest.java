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
public class StatusRequest {
    @NotNull
    @Size(max = 8, min = 2, message = "Status name should be from 2 to 8 characters!")
    private String statusName;
}
