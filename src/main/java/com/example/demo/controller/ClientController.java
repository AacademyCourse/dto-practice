package com.example.demo.controller;

import com.example.demo.dto.ClientRequest;
import com.example.demo.dto.LoginRequest;
import com.example.demo.entity.Client;
import com.example.demo.exception.RecordNotFoundException;
import com.example.demo.service.ClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.management.relation.RoleNotFoundException;

@RestController
@RequestMapping(path = "/client")
public class ClientController {
    @Autowired
    ClientService clientService;

    @PostMapping(path = "/save")
    Client save(@RequestBody @Valid ClientRequest clientRequest) throws RecordNotFoundException {
        return clientService.saveClient(clientRequest);
    }

    @PostMapping(path = "/login")
    Client login(@RequestBody @Valid LoginRequest loginRequest) throws RecordNotFoundException {
        return clientService.login(loginRequest);
    }

    @PreAuthorize("ADMIN")
    Client get(){
        return new Client();
    }
}
