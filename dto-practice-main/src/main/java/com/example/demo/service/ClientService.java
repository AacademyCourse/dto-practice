package com.example.demo.service;

import com.example.demo.dto.ClientRequest;
import com.example.demo.dto.LoginRequest;
import com.example.demo.entity.Client;
import com.example.demo.exception.RecordNotFoundException;



public interface ClientService {
    Client saveClient(ClientRequest client) throws RecordNotFoundException;
    Client updateClient(Client client) throws RecordNotFoundException;
    Client getClient(Long id);
    void deleteClient(Long id);
    Client login(LoginRequest loginRequest) throws RecordNotFoundException;
}
