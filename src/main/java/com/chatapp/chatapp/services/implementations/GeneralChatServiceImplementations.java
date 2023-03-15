package com.chatapp.chatapp.services.implementations;

import com.chatapp.chatapp.Dto.GeneralChatDto;
import com.chatapp.chatapp.models.Client;
import com.chatapp.chatapp.models.GeneralChat;
import com.chatapp.chatapp.repositories.ClientRepository;
import com.chatapp.chatapp.repositories.GeneralChatRepository;
import com.chatapp.chatapp.services.GeneralChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GeneralChatServiceImplementations implements GeneralChatService {

    @Autowired
    ClientRepository clientRepository;
    @Autowired
    GeneralChatRepository generalChatRepository;
    @Override
    public List<GeneralChatDto> findAll() {
        return generalChatRepository.findAll().stream().map(message -> new GeneralChatDto(message)).collect(Collectors.toList());
    }

    @Override
    public ResponseEntity<Object> addMessage(Authentication authentication, String message) {
        Client client = clientRepository.findByEmail(authentication.getName());
        GeneralChat generalChat1 = new GeneralChat(message, LocalDateTime.now(), client);

        clientRepository.save(client);

        generalChatRepository.save(generalChat1);

        return new ResponseEntity<>("Message added", HttpStatus.CREATED);
    }


}
