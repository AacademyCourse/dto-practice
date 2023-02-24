package com.example.demo.service;

import com.example.demo.dto.ClientPasswordUpdate;
import com.example.demo.dto.ClientRequest;
import com.example.demo.dto.ClientResponse;
import com.example.demo.dto.LoginRequest;
import com.example.demo.entity.Client;

public interface ClientService {

    ClientResponse saveClient(ClientRequest client);
    void updateClient(ClientPasswordUpdate clientPasswordUpdate);
    ClientResponse getClient(Long id);
    void deleteClient(Long id);
    ClientResponse login (LoginRequest loginRequest);

}
