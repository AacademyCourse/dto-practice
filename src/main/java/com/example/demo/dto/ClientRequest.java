package com.example.demo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ClientRequest {
    @Size(min =2, message = "Client first name should contain more than 2 characters")
    private String firstName;
    @Size(min =3, message = "Client first name should contain more than 2 characters")
    private String lastName;
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+.[a-z]{2,3}",
            flags = Pattern.Flag.CASE_INSENSITIVE, message = "Email should have proper email format")
    private String email;
    @Pattern(regexp = "^(?=.*?[A-Z])(?=(.*[a-z]){1,})(?=(.*[\\d]){1,})(?=(.*[\\W]){1,})(?!.*\\s).{8,}$", message = """
            At least one upper case English letter
            At least one lower case English letter
            At least one digit
            At least one special character
            Minimum eight in length""")
    private String password;
    private String status; //no need to be Status
    private String address;

}
