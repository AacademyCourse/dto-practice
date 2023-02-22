package com.example.demo.service;

import com.example.demo.dto.ClientRequest;
import com.example.demo.entity.Client;

public interface ClientService {
    Client saveClient(ClientRequest client);
    Client updateClient(Client client);
    Client getClient(Long id);
    Client deleteClient(Long id);

}
