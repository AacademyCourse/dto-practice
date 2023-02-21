package com.example.demo.dto;

import jakarta.validation.constraints.NotNull;
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
    String statusName;
}
