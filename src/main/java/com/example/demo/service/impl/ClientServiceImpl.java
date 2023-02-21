package com.example.demo.service.impl;

import com.example.demo.entity.Client;
import com.example.demo.repository.ClientRepository;
import com.example.demo.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }


    @Override
    public Set<Client> getClients() {
        return new HashSet<>(clientRepository.findAll());
    }

    @Override
    public Client getClient(Long id) {
        Client searchedClient = new Client();
        if (clientRepository.findById(id).isPresent()) {
            searchedClient = clientRepository.findById(id).get();
        }
        return searchedClient;
    }

    @Override
    public Client addClient(Client client) {
        Client newClient = new Client();
        newClient.setFistName(client.getFistName());
        newClient.setLastName(client.getLastName());
        newClient.setIban(client.getIban());
        newClient.setBalance(client.getBalance());
        newClient.setAddress(client.getAddress());
        newClient.setStatuses(client.getStatuses());
        newClient.setTransactions(client.getTransactions());
        return clientRepository.save(newClient);
    }

    @Override
    public Client modifyClient(Long id, Client client) {
        Client modedClient = new Client();
        modedClient.setId(client.getId());
        modedClient.setFistName(client.getFistName());
        modedClient.setLastName(client.getLastName());
        modedClient.setIban(client.getIban());
        modedClient.setBalance(client.getBalance());
        modedClient.setAddress(client.getAddress());
        modedClient.setStatuses(client.getStatuses());
        modedClient.setTransactions(client.getTransactions());
        return clientRepository.save(modedClient);
    }

    @Override
    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }
}
