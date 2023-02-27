package com.example.demo.dto;

import com.example.demo.entity.Status;
import lombok.*;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ClientResponse {

    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String address;
    private Set<Status> statuses;
}
