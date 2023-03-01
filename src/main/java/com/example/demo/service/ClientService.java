package com.example.demo.service;

import com.example.demo.dto.ClientPasswordUpdate;
import com.example.demo.dto.ClientRequest;
import com.example.demo.dto.ClientResponse;
import com.example.demo.dto.LoginRequest;
import com.example.demo.entity.Client;
import com.example.demo.exception.RecordNotFoundException;



public interface ClientService  {

    ClientResponse saveClient(ClientRequest client) throws RecordNotFoundException;
    void updateClient(ClientPasswordUpdate client) throws RecordNotFoundException;
    ClientResponse getClient(Long id) throws RecordNotFoundException;
    void deleteClient(Long id);
    ClientResponse login(LoginRequest loginRequest) throws RecordNotFoundException;
    Client findByEmail(String email);


}
