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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class ClientServiceImpl implements ClientService {
   private final ClientConvertor clientConvertor;
   private final StatusService statusService;
   private final ClientRepository clientRepository;
   private final BCryptPasswordEncoder bCryptPasswordEncoder;

   @Autowired
    public ClientServiceImpl(ClientConvertor clientConvertor, StatusService statusService, ClientRepository clientRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.clientConvertor = clientConvertor;
        this.statusService = statusService;
        this.clientRepository = clientRepository;
       this.bCryptPasswordEncoder = bCryptPasswordEncoder;
   }

    @Override
    public Client saveClient(ClientRequest client) throws RecordNotFoundException {
         //Check if status exists in status table
//         Status status = statusService.findByName(client.getStatus()).orElseThrow(StatusNotFoundException::new);
        Optional<Status> status = statusService.findByName(client.getStatus());
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
