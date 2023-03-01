package com.example.demo.dto;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.stereotype.Component;





@Component
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ClientRequest {
    @Size(min = 2, message = "Client first name should contain more than 2 characters")
    private String firstName;
    @Size(min = 3, message = "Client last name should contain more than 3 characters")
    private String lastName;
    @Size(min= 3, message = "Email should have proper email format.")
    private String email;
    @Pattern(regexp = "^(?=.*?[A-Z])(?=(.*[a-z]){1,})(?=(.*[\\d]){1,})(?=(.*[\\W]){1,})(?!.*\\s).{8,}$", message = "" +
            "At least one upper case English letter\n" +
            "At least one lower case English letter\n" +
            "At least one digit\n" +
            "At least one special character\n" +
            "Minimum eight in length")
    private String password;
    @Size(min = 3, message = "Status should contain at least 3 characters")
    private String status;
    private String address;
}
