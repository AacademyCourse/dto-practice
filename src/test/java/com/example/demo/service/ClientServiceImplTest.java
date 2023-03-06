package com.example.demo.service;

import com.example.demo.converter.ClientConvertor;
import com.example.demo.dto.ClientRequest;
import com.example.demo.entity.Client;
import com.example.demo.entity.Status;
import com.example.demo.exception.RecordNotFoundException;
import com.example.demo.exception.StatusNotFoundException;
import com.example.demo.repository.ClientRepository;
import com.example.demo.service.impl.ClientServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith({MockitoExtension.class})
public class ClientServiceImplTest {

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private ClientConvertor clientConvertor;

    @Mock
    private StatusService statusService;

    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private ClientService clientService;

    @BeforeEach
    public void setUp() {
        clientService = new ClientServiceImpl(clientRepository, clientConvertor, statusService, bCryptPasswordEncoder);
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
        assertTrue(Set.of(Client.builder().build()).size() == clients.size(), "Type of response");
        assertEquals(Set.of(Client.builder().build()), clients, "message");
    }

    @Test
    public void getClientByIdSuccess() {
        Long id = 1L;
        when(clientRepository.findById(id)).thenReturn(Optional.of(Client.builder().build()));
        Client client = clientService.getClient(id);
        verify(clientRepository, times(1)).findById(id);
        assertNotNull(client, "User should not be null");
    }

    @Test
    public void getClientByIdErrorMessageTest() {
        Long id = 1L;
        Throwable ex = assertThrows(RecordNotFoundException.class, () -> {
            clientService.getClient(id);
        });
        assertEquals("User not found or invalid credentials", ex.getMessage());

    }

    @Test
    public void findClientByEmailSuccess() {
        String email = "test@gmail.com";
        when(clientRepository.findByEmail(email)).thenReturn(Optional.of(Client.builder().email(email).build()));
        Client client = clientService.findByEmail(email);
        verify(clientRepository,times(1)).findByEmail(email);
        assertNotNull(client);
        assertEquals(email, client.getEmail());
    }

    @Test
    public void getClientByEmailErrorMessageTest() {
        String email = "test@gmail.com";
        Client client = clientService.findByEmail(email);

        Throwable ex = assertThrows(UsernameNotFoundException.class, () -> {
            clientService.findByEmail(email);
        });

        verify(clientRepository,times(1)).findByEmail(email);
        assertEquals("User not found", ex.getMessage());
    }

    @Test
    public void saveClient() throws StatusNotFoundException {
        ClientRequest clientRequest = buildClientRequest();
        Status status = new Status();
        clientService.saveClient(clientRequest);
        verify(clientConvertor, times(1)).convertToClient(clientRequest);

    }


    private ClientRequest buildClientRequest() {
        return ClientRequest.builder()
                .firstName("aaaaa")
                .lastName("ggggggg")
                .address("addresssss")
                .password("th832hgoa!@#$")
                .email("ggg@gbg.bg")
                .status("VIP")
                .build();
    }
}
