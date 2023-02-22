package com.example.demo.controller;

import com.example.demo.converter.ClientConverter;
import com.example.demo.dto.ClientRequest;
import com.example.demo.dto.ClientResponse;
import com.example.demo.dto.LoginRequest;
import com.example.demo.entity.Client;
import com.example.demo.exception.RecordNotFoundException;
import com.example.demo.exception.StatusNotFoundException;
import com.example.demo.service.impl.ClientServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.relation.RoleNotFoundException;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping(path = "/clients")
public class ClientController {
    @Autowired
    private ClientServiceImpl clientService;

    @Autowired
    private ClientConverter clientConverter;

    @GetMapping(path = "/all")
    public ResponseEntity<Set<ClientResponse>> getClients() {
        Set<ClientResponse> clientResponses = new HashSet<>();
        Set<Client> clients = clientService.getClients();
        clients.forEach(
                client -> clientResponses.add(clientConverter.toClientResponse(client))
        );
        return ResponseEntity.status(HttpStatus.OK).body(clientResponses);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ClientResponse> getClient(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(clientConverter.toClientResponse(clientService.getClient(id)));
    }

    @PostMapping(path = "/add")
    public ResponseEntity<ClientResponse> saveClient(@RequestBody @Valid ClientRequest client) throws StatusNotFoundException {
        Client newClient = clientService.saveClient(client);
        return ResponseEntity.status(HttpStatus.CREATED).body(clientConverter.toClientResponse(newClient));
    }

    @PostMapping(path = "/login")
    public Client login(@RequestBody @Valid LoginRequest loginRequest) throws RecordNotFoundException {
        return clientService.login(loginRequest);
    }

    @PutMapping (path = "/{id}/update")
    public Client updateClient(@PathVariable Long id, @RequestBody @Valid ClientRequest client) {
        Client getClient = clientService.getClient(id);
        getClient.setFistName(client.getFirstName());
        getClient.setLastName(client.getLastName());
        getClient.setAddress(client.getAddress());
        return clientService.updateClient(id, getClient);
    }

    @DeleteMapping (path = "{id}/delete")
    public ResponseEntity<String> deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
        return ResponseEntity.status(HttpStatus.OK).body("Client deleted");
    }
}