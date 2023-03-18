package com.chatapp.chatapp.services;

import com.chatapp.chatapp.Dto.ClientDto;
import com.chatapp.chatapp.Dto.ChatClients;
import com.chatapp.chatapp.Dto.PrivateConversationDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface ClientService {



    List<ClientDto> findAll();

    ClientDto getClient(Long id);

    ResponseEntity<Object> registerClient(String nickName, String email, String password);

    ClientDto getCurrentClient(Authentication authentication);

    ResponseEntity<Object> clientLoggedStatus(Authentication authentication);

    ResponseEntity<Object> clientLogoutStatus(Authentication authentication);


    List<ChatClients> getChatClients(Authentication authentication);


    List<PrivateConversationDto> getCurrentPrivateConversations(Authentication authentication);

    List<ChatClients> filterOnlineClients(Authentication authentication, String nickName, Boolean searchFriends);


}
