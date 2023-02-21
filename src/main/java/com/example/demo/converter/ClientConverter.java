package com.example.demo.converter;

import com.example.demo.dto.ClientRequest;
import com.example.demo.dto.ClientResponse;
import com.example.demo.entity.Client;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashSet;

@Component
@NoArgsConstructor
public class ClientConverter {
    public Client convertToClient(ClientRequest clientRequest) {
        return Client.builder().fistName(clientRequest.getFirstName())
                .lastName(clientRequest.getLastName())
                .address(clientRequest.getAddress())
                .iban(clientRequest.getIban())
                .transactions(new HashSet<>())
                .statuses(new HashSet<>())
                .balance(BigDecimal.ZERO)
                .build();
    }

    public ClientResponse toClientResponse(Client client) {
        return ClientResponse.builder().firstName(client.getFistName())
                .lastName(client.getLastName())
                .address(client.getAddress())
                .build();
    }
}
