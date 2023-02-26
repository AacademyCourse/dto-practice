package com.example.demo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ClientRequest {

    @Size(min = 2, message = "Client first name should contain more than 2 characters")
    private String firstName;
    @Size(min = 3, message = "Client last name should contain more than 3 characters")
    private String lastName;
    @Email(message = "Email should have proper email format.")
    private String email;
    @Pattern(regexp = "(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,}",
            message = " Password should contain, \n" +
                    "Min 1 uppercase letter.\n" +
                    "Min 1 lowercase letter.\n" +
                    "Min 1 special character.\n" +
                    "Min 1 number.\n" +
                    "Min 8 characters.\n" +
                    "Max 30 characters.")
            private String password;
            private String status;
            private String address;
}
