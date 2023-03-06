package com.example.demo.converter;

import com.example.demo.dto.ClientRequest;
import com.example.demo.dto.ClientResponse;
import com.example.demo.entity.Client;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.UUID;

@Component
@NoArgsConstructor
public class ClientConvertor {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    public Client convertToClient(ClientRequest clientRequest) {
        UUID uuid = UUID.randomUUID();
        return Client.builder().fistName(clientRequest.getFirstName())
                .lastName(clientRequest.getLastName())
                .email(clientRequest.getEmail())
                .password(bCryptPasswordEncoder.encode(clientRequest.getPassword()))
                .address(clientRequest.getAddress())
                .iban(uuid.toString())
                .transactions(new HashSet<>())
                .statuses(new HashSet<>())
                .balance(new BigDecimal("0.0"))
                .build();
    }

    public ClientResponse toClientResponse(Client client) {
        return ClientResponse.builder().firstName(client.getFistName())
                .lastName(client.getLastName())
                .address(client.getAddress())
                .build();
    }

}
