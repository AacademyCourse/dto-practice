package com.example.demo.controller;

import com.example.demo.convertor.ClientConvertor;
import com.example.demo.dto.*;
import com.example.demo.entity.Client;
import com.example.demo.service.ClientService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/client")
public class ClientController {

    @Autowired
    ClientService clientService;
    @Autowired
    ClientConvertor clientConvertor;

    @PostMapping(path = "/save")
    ResponseEntity<ClientResponse> save(@Valid @RequestBody ClientRequest clientRequest) {
        Client client = clientConvertor.toClient(clientRequest);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(clientConvertor.toResponse(clientService.saveClient(client)));
    }

    @PostMapping(path = "/login")
    ResponseEntity<ClientResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(clientService.login(loginRequest));
    }
    @Transactional
    @PutMapping(path = "/pswd")
    ResponseEntity<String> updateClient(@Valid @RequestBody ClientPasswordUpdate client) {
        clientService.updateClient(client);
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body("Password changed");
    }

    @GetMapping(path = "/{id}")
    ResponseEntity<ClientResponse> getClient(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(clientConvertor.toResponse(clientService.getClient(id)));
    }

    @GetMapping(path = "/email/{name}")
    ResponseEntity<ClientResponse> getByEmail(@PathVariable @Valid String name) {
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(clientConvertor.toResponse(clientService.findByEmail(name)));
    }


    @DeleteMapping(path = "/{id}")
    ResponseEntity<String> deleteClient(@PathVariable Long id){
        clientService.deleteClient(id);
        return ResponseEntity
                .ok()
                .body(String.format("%d deleted", id));
    }

}
