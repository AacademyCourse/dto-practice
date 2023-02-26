package com.example.demo.service.impl;

import com.example.demo.converter.ClientConverter;
import com.example.demo.converter.StatusConverter;
import com.example.demo.dto.ClientRequest;
import com.example.demo.dto.LoginRequest;
import com.example.demo.entity.Client;
import com.example.demo.entity.Status;
import com.example.demo.exception.RecordNotFoundException;
import com.example.demo.exception.StatusNotFoundException;
import com.example.demo.repository.ClientRepository;
import com.example.demo.service.ClientService;
import com.example.demo.service.StatusService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.management.relation.RoleNotFoundException;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

@Service
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;

    private final ClientConverter clientConverter;

    private final StatusService statusService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository, ClientConverter clientConverter, StatusService statusService) {
        this.clientConverter = clientConverter;
        this.statusService = statusService;
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
    public Client login(LoginRequest loginRequest) throws RecordNotFoundException {
        Optional<Client> client = clientRepository.findByEmail(loginRequest.getEmail());
        if (client.isEmpty()) {
            throw new RecordNotFoundException("User not found or invalid credentials");
        }
        else if (!bCryptPasswordEncoder.matches(loginRequest.getPassword(), client.get().getPassword())){
            throw new RecordNotFoundException("User not found or invalid credentials");
        }
        return client.get();
    }

    @Override
    public Client saveClient(ClientRequest client) throws StatusNotFoundException {
        Status status;
        Optional<Status> status1 = statusService.findStatusByName(client.getStatus());
        if (status1.isPresent()) {
            status = status1.get();
        } else {
            throw new StatusNotFoundException(String.format("Status %s not found", client.getStatus()));
        }
        Client clientToBeSaved = clientConverter.convertToClient(client);
        clientToBeSaved.setStatuses(Set.of(status));
        return clientRepository.save(clientToBeSaved);
    }

    @Override
    public Client updateClient(Long id, ClientRequest client) throws StatusNotFoundException {
        Status status;
        Optional<Status> status1 = statusService.findStatusByName(client.getStatus());
        if (status1.isPresent()) {
            status = status1.get();
        } else {
            throw new StatusNotFoundException(String.format("Status %s not found", client.getStatus()));
        }
        Client modedClient = getClient(id);
        modedClient.setFistName(client.getFirstName());
        modedClient.setLastName(client.getLastName());
        modedClient.setEmail(client.getEmail());
        modedClient.setPassword(bCryptPasswordEncoder.encode(client.getPassword()));
        modedClient.setAddress(client.getAddress());
        modedClient.setStatuses(Set.of(status));
        return clientRepository.save(modedClient);
    }

    @Override
    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }
}
