package com.example.demo.service.impl;

import com.example.demo.convertor.ClientConvertor;
import com.example.demo.entity.Client;
import com.example.demo.entity.Status;
import com.example.demo.exception.RecordNotFoundException;
import com.example.demo.repository.ClientRepository;
import com.example.demo.dto.ClientPasswordUpdate;
import com.example.demo.dto.ClientRequest;
import com.example.demo.dto.ClientResponse;
import com.example.demo.dto.LoginRequest;
import com.example.demo.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class ClientServiceImpl implements ClientService {
    private final ClientConvertor clientConvertor;
    private final StatusServiceImpl statusService;
    private final ClientRepository clientRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public ClientServiceImpl(ClientConvertor clientConvertor, StatusServiceImpl statusService, ClientRepository clientRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.clientConvertor = clientConvertor;
        this.statusService = statusService;
        this.clientRepository = clientRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;

    }

    @Override
    public ClientResponse saveClient(ClientRequest clientRequest) throws RecordNotFoundException {
        Status status = statusService.findByName(clientRequest.getStatus());
        Client clientToBeSaved = clientConvertor.toClient(clientRequest);
        clientToBeSaved.setStatuses(Set.of(status));
        return clientConvertor.toResponse(clientRepository.save(clientToBeSaved));

    }

    @Override
    public ClientResponse login(LoginRequest loginRequest) throws RecordNotFoundException {
        Optional<Client> client = clientRepository.findByEmail(loginRequest.getEmail());
        if (client.isEmpty()) {
            throw new RecordNotFoundException("User not found or invalid credentials");
        } else if (!bCryptPasswordEncoder.matches(loginRequest.getPassword(), client.get().getPassword())) {
            throw new RecordNotFoundException("User not found or invalid credentials");
        }
        return clientConvertor.toResponse(client.get());
    }

    @Override
    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }

    @Override
    public void updateClient(ClientPasswordUpdate clientPasswordUpdate) throws RecordNotFoundException{
        Optional<Client> client = clientRepository.findById(clientPasswordUpdate.getId());
        if (client.isEmpty()) {
            throw new RecordNotFoundException("User not found or invalid credentials");
        } else if (!bCryptPasswordEncoder.matches(
                clientPasswordUpdate.getPassword(),
                client.get().getPassword())) {
            throw new RecordNotFoundException("User not found or password is wrong");
        } else {
            client.get().setPassword(clientPasswordUpdate.getCreatePassword());
        }
    }

    @Override
    public ClientResponse getClient(Long id) throws RecordNotFoundException {
        return clientConvertor.toResponse(clientRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(String.format("Id %s not found", id))));
    }

    @Override
    public Client findByEmail(String email) {
        return clientRepository.findByEmail(email).orElseThrow(()->new UsernameNotFoundException("User not found"));
    }
}
