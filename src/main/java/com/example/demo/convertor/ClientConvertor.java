package com.example.demo.convertor;


import com.example.demo.dto.ClientRequest;
import com.example.demo.dto.ClientResponse;
import com.example.demo.entity.Client;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;


import java.util.UUID;
@RequiredArgsConstructor
@Component
public class ClientConvertor {

    public Client toClient(ClientRequest clientRequest){
        UUID uuid = UUID.randomUUID();
        return Client.builder().firstName(clientRequest.getFirstName())
                .lastName(clientRequest.getLastName())
                .address(clientRequest.getAddress())
                .email(clientRequest.getEmail())

                .iban(uuid.toString()) //sets the iban
                .password(clientRequest.getPassword())
                .balance(new BigDecimal("0"))    //default = 0
                .build();
    }

    public ClientResponse toResponse (Client client){
        return ClientResponse.builder()
                .id(client.getId())
                .firstName(client.getFirstName())
                .lastName(client.getLastName())
                .address(client.getAddress())
                .email(client.getEmail())
                .statuses(client.getStatuses())
                .build();
    }


}
