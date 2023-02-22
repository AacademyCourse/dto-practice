package com.example.demo.service.impl;

import com.example.demo.dto.ClientRequest;
import com.example.demo.entity.Client;
import com.example.demo.service.ClientService;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl implements ClientService {
    @Override
    public Client saveClient(ClientRequest client) {

    }

    @Override
    public Client updateClient(Client client) {
        return null;
    }

    @Override
    public Client getClient(Long id) {
        return null;
    }

    @Override
    public Client deleteClient(Long id) {
        return null;
    }
}
