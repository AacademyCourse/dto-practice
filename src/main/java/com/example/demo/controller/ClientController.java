package com.example.demo.controller;


import com.example.demo.dto.ClientPasswordUpdate;
import com.example.demo.dto.ClientRequest;
import com.example.demo.dto.ClientResponse;
import com.example.demo.dto.LoginRequest;
import com.example.demo.exception.RecordNotFoundException;
import com.example.demo.service.ClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/clients")
public class ClientController {
    @Autowired
    ClientService clientService;


    @PostMapping
    ResponseEntity<ClientResponse> save(@RequestBody @Valid ClientRequest clientRequest) throws RecordNotFoundException {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(clientService.saveClient(clientRequest));
    }

    @PostMapping(path = "/login")
    ResponseEntity<ClientResponse> login(@RequestBody @Valid LoginRequest loginRequest) throws RecordNotFoundException{
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(clientService.login(loginRequest));
    }


    @GetMapping(path = "/{id}")
    ResponseEntity<ClientResponse> getClient(@PathVariable Long id) throws RecordNotFoundException{
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(clientService.getClient(id));
    }

    @DeleteMapping(path = "/{id}")
    ResponseEntity<String> deleteClient (@PathVariable Long id) {
        clientService.deleteClient(id);
        return ResponseEntity
                .ok()
                .body(String.format("Client deleted", id));
    }

    @PutMapping(path = "/update")
    ResponseEntity<String> updateClient(@RequestBody @Valid ClientPasswordUpdate client) throws RecordNotFoundException{
        clientService.updateClient(client);
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(String.format("Password is successfully changed"));
    }

}

