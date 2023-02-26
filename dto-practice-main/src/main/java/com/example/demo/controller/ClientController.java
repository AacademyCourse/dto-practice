package com.example.demo.controller;

import com.example.demo.dto.ClientRequest;
import com.example.demo.dto.LoginRequest;
import com.example.demo.entity.Client;
import com.example.demo.exception.RecordNotFoundException;
import com.example.demo.service.ClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Lazy
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

    @PutMapping(path = "/update")
    ResponseEntity<String> updateClient(@RequestBody @Valid Client client) throws RecordNotFoundException {
        clientService.updateClient(client);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Client changed");
    }

    @GetMapping(path = "/{id}")
    ResponseEntity<Client> getClient(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(clientService.getClient(id));
    }
    @DeleteMapping(path = "/{id}")
    ResponseEntity<String> deleteClient(@PathVariable Long id){
        clientService.deleteClient(id);
        return ResponseEntity.status(HttpStatus.OK).body("Client deleted");
    }
}

