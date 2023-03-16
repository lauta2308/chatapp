package com.chatapp.chatapp.services;

import com.chatapp.chatapp.Dto.ClientDto;
import com.chatapp.chatapp.Dto.ClientOnlineDto;
import com.chatapp.chatapp.Dto.PrivateConversationDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import java.util.List;
import java.util.Set;

public interface ClientService {



    List<ClientDto> findAll();

    ClientDto getClient(Long id);

    ResponseEntity<Object> registerClient(String nickName, String email, String password);

    ClientDto getCurrentClient(Authentication authentication);

    ResponseEntity<Object> clientLoggedStatus(Authentication authentication);

    ResponseEntity<Object> clientLogoutStatus(Authentication authentication);


    List<ClientOnlineDto> getOnlineClients(Authentication authentication);


    List<PrivateConversationDto> getCurrentPrivateConversations(Authentication authentication);



}
