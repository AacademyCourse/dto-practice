package com.example.demo.service;

import com.example.demo.convertor.ClientConvertor;

import com.example.demo.dto.ClientRequest;
import com.example.demo.dto.ClientResponse;
import com.example.demo.entity.Client;
import com.example.demo.entity.Status;
import com.example.demo.exception.RecordNotFoundException;
import com.example.demo.repository.ClientRepository;
import com.example.demo.service.impl.ClientServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

@ExtendWith({MockitoExtension.class})
@MockitoSettings(strictness = Strictness.LENIENT)

public class ClientServiceTest {
    @Mock
    private ClientRepository clientRepository;
    @Mock
    private StatusService statusService;
    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Mock
    private ClientConvertor clientConverter;

    private ClientService clientService;

    private String email = "test@gmail.com";


    @BeforeEach
    public void setUp() {
        clientService = new ClientServiceImpl(
                clientRepository,
                clientConverter,
                statusService,
                bCryptPasswordEncoder);
    }

    @Test
    public void getAllClients() {
        clientService.getClients();
        verify(clientRepository, times(1)).findAll();

    }

    @Test
    public void getClients() {
        when(clientRepository.findAll()).thenReturn(List.of(Client.builder().build()));
        Set<Client> clients = clientService.getClients();
        verify(clientRepository, times(1)).findAll();
        assertTrue(Set.of(Client.builder().build()).size() == clients.size());
    }

    @Test
    public void getClientByIDSuccess() throws RecordNotFoundException {
        Long id = 1L;
        when(clientRepository.findById(id)).thenReturn(Optional.of(Client.builder().email("test@gmail.com").build()));
        clientService.getClient(id);
        verify(clientRepository, times(1)).findById(id);
//        assertNotNull("User should not be null",client);
    }

    @Test
    public void getClientByIdErrorMessageTest() {
        Long id = 1L;

        Throwable ex = assertThrows(RecordNotFoundException.class, () -> {
            clientService.getClient(id);
        });
        assertEquals("Id " + id + " not found", ex.getMessage());
    }

    @Test
    public void findClientByEmailSuccess(){

        String email = "test@gmail.com";
        when(clientRepository.findByEmail(email)).thenReturn(Optional.of(Client.builder().email(email).build()));
        Client client = clientService.findByEmail(email);

        verify(clientRepository,times(1)).findByEmail(email);
        assertNotNull(client);
        assertEquals(email,client.getEmail());

    }

    @Test
    public void findClientByEmailExeption(){
        Throwable ex = assertThrows(UsernameNotFoundException.class, () -> {
            clientService.findByEmail(email);
        });

        verify(clientRepository,times(1)).findByEmail(email);
        assertEquals("User not found",ex.getMessage());
    }
    @Test
    public void saveClient() throws RecordNotFoundException {
        ClientRequest clientRequest = buildClientRequest();
        Client client = buildClient();

        when(clientConverter.toClient(clientRequest)).thenReturn(client);
        when(clientRepository.save(client)).thenReturn(client);
        when(clientConverter.toResponse(client)).thenReturn(buildResponse(client));
        when(statusService.findByName("name")).thenReturn(Status.builder().statusName("name").build());
        when(bCryptPasswordEncoder.encode("password")).thenReturn("asdadqweadavadfcafea");
        ClientResponse resp = clientService.saveClient(clientRequest);
        verify(clientRepository,times(1)).save(client);
        assertEquals(resp.getEmail(),client.getEmail());
    }


    private ClientRequest buildClientRequest(){
        ClientRequest clreq = new ClientRequest();
        clreq.setFirstName("Test");
        clreq.setLastName("TESTOV");
        clreq.setAddress("Test address");
        clreq.setEmail(email);
        clreq.setPassword("password");
        return clreq;
    }
    private Client buildClient(){
        Client clreq = new Client();
        clreq.setId(1L);
        clreq.setFirstName("Test");
        clreq.setLastName("TESTOV");
        clreq.setAddress("Test address");
        clreq.setEmail(email);
        clreq.setPassword("password");
        return clreq;
    }
    private ClientResponse buildResponse(Client client){
       return clientConverter.toResponse(client);
    }


}
