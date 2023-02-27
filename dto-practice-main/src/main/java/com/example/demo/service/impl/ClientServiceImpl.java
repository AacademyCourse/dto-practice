package com.example.demo.service.impl;

import com.example.demo.convertor.ClientConvertor;
import com.example.demo.dto.ClientRequest;
import com.example.demo.dto.LoginRequest;
import com.example.demo.entity.Client;
import com.example.demo.entity.Status;
import com.example.demo.exception.RecordNotFoundException;
import com.example.demo.repository.ClientRepository;
import com.example.demo.service.ClientService;
import com.example.demo.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
@Lazy
@Service
public class ClientServiceImpl implements ClientService {
    private final ClientConvertor clientConvertor;
    private final StatusService statusService;
    private final ClientRepository clientRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public ClientServiceImpl(ClientConvertor clientConvertor, StatusService statusService, ClientRepository clientRepository, ClientService clientService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.clientConvertor = clientConvertor;
        this.statusService = statusService;
        this.clientRepository = clientRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    public Client saveClient(ClientRequest client) throws RecordNotFoundException {
        //Check if status exists in status table
        Optional<Status> status = Optional.ofNullable(statusService.findByStatusName(client.getStatus()));
        if(status.isPresent()){
            status.get();
        } else {
            throw new RecordNotFoundException(String.format("Status %s not found",client.getStatus()));
        }
        Client clientToBeSaved = clientConvertor.toClient(client);
        clientToBeSaved.setStatuses(Set.of(status.get()));
        return clientRepository.save(clientToBeSaved);
    }

    public Client login (LoginRequest loginRequest) throws RecordNotFoundException {
        Optional<Client> client = clientRepository.findByEmail(loginRequest.getEmail());
        if(client.isEmpty()){
            throw new RecordNotFoundException("User not found or invalid credentials");
        }
        else if(!bCryptPasswordEncoder.matches(loginRequest.getPassword(),client.get().getPassword())){
            throw new RecordNotFoundException("User not found or invalid credentials");
        }
        return client.get();
    }

    @Override
    public Client updateClient(Client client) throws RecordNotFoundException {
        Optional<Client> clients = clientRepository.findById(client.getId());
        if (clients.isEmpty()) {
            throw new RecordNotFoundException("User not found or does not exist");
        } else if (!bCryptPasswordEncoder.matches(
                client.getPassword(),
                clients.get().getPassword())) {
            throw new RecordNotFoundException("User not found");
        } else if (clients.equals(client)){
            Client updateClient = new Client();
            updateClient.setFirstName(client.getFirstName());
            updateClient.setLastName(client.getLastName());
            updateClient.setBalance(client.getBalance());
            updateClient.setAddress(client.getAddress());
            updateClient.setPassword(client.getPassword());
            updateClient.setStatuses(client.getStatuses());
            return clientRepository.save(updateClient);
        }
        return clients.get();
    }
    @Override
    public Client getClient(Long id) {
        return clientRepository.findById(id).orElseThrow(RuntimeException::new);
    }
    @Override
    public void deleteClient(Long id) {
       clientRepository.deleteById(id);
    }
}
