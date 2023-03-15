package com.chatapp.chatapp.services.implementations;

import com.chatapp.chatapp.Dto.ClientDto;
import com.chatapp.chatapp.Dto.ClientOnlineDto;
import com.chatapp.chatapp.Dto.PrivateConversationDto;
import com.chatapp.chatapp.models.Client;
import com.chatapp.chatapp.models.ClientStatus;
import com.chatapp.chatapp.models.PrivateConversation;
import com.chatapp.chatapp.repositories.ClientRepository;
import com.chatapp.chatapp.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ClientServiceImplementation implements ClientService {

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public List<ClientDto> findAll() {
        return clientRepository.findAll().stream().map(client -> new ClientDto(client)).collect(Collectors.toList());
    }




    @Override
    public ClientDto getClient(Long id) {
        return new ClientDto(clientRepository.findById(id). orElse(null));
    }

    @Override
    public ResponseEntity<Object> registerClient(String nickName, String email, String password) {
        Client client = new Client(nickName, email, passwordEncoder.encode(password));
        clientRepository.save(client);
        return new ResponseEntity<>("Usuario creado con éxito", HttpStatus.CREATED);
    }

    @Override
    public ClientDto getCurrentClient(Authentication authentication) {
        return new ClientDto(clientRepository.findByEmail(authentication.getName()));
    }

    @Override
    public ResponseEntity<Object> clientLoggedStatus(Authentication authentication) {
        Client client = clientRepository.findByEmail(authentication.getName());

        client.setClientStatus(ClientStatus.ONLINE);
        clientRepository.save(client);
        return new ResponseEntity<>("User online status", HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Object> clientLogoutStatus(Authentication authentication) {
        Client client = clientRepository.findByEmail(authentication.getName());

        client.setClientStatus(ClientStatus.OFFLINE);
        clientRepository.save(client);
        return new ResponseEntity<>("User offline status", HttpStatus.CREATED);
    }

    @Override
    public Set<ClientOnlineDto> getOnlineClients(Authentication authentication) {
     Set<Client> clientsOnline =  clientRepository.findAll().stream().filter(client -> client.getClientStatus() == ClientStatus.ONLINE).collect(Collectors.toSet());

       return clientsOnline.stream().map(client -> new ClientOnlineDto(client)).collect(Collectors.toSet());
    }

    @Override
    public List<PrivateConversationDto> getCurrentPrivateConversations(Authentication authentication) {
        Client client = clientRepository.findByEmail(authentication.getName());
        ClientDto clientDto = new ClientDto(client);

        List <PrivateConversationDto> privateChatsList = new ArrayList<>();


        privateChatsList.addAll(clientDto.getPrivateConversations());

        privateChatsList = privateChatsList
                .stream()
                .sorted(Comparator.comparing(PrivateConversationDto::getLastChange).reversed())
                .collect(Collectors.toList());


        return privateChatsList;




    }



}
