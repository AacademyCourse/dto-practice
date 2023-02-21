package com.example.demo.dto;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class StatusResponse {
    Long id;
    String statusName;
}
