package com.example.demo.dto;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ClientResponse {
    private String firstName;
    private String lastName;
    private String address;
}
