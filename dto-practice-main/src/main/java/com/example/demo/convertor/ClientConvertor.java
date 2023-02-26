package com.example.demo.convertor;

import com.example.demo.dto.ClientRequest;
import com.example.demo.entity.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.UUID;

@Component
public class ClientConvertor {

    @Autowired
   BCryptPasswordEncoder bCryptPasswordEncoder;

       public Client toClient(ClientRequest clientRequest){
           UUID uuid = UUID.randomUUID();
           Client client = Client.builder()
                   .firstName(clientRequest.getFirstName())
                   .lastName(clientRequest.getLastName())
                   .address(clientRequest.getAddress())
                   .email(clientRequest.getEmail())
                   .iban(uuid.toString())
                   .password(bCryptPasswordEncoder.encode(
                            clientRequest.getPassword()))
                   .balance(new BigDecimal("0"))
                   .build();
           return client;
       }
}
