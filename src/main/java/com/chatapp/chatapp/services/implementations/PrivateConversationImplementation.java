package com.chatapp.chatapp.services.implementations;


import com.chatapp.chatapp.Dto.PrivateConversationDto;
import com.chatapp.chatapp.models.*;
import com.chatapp.chatapp.repositories.ClientRepository;
import com.chatapp.chatapp.repositories.PrivateConversationRepository;
import com.chatapp.chatapp.repositories.PrivateMessageRepository;
import com.chatapp.chatapp.services.PrivateConversationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
public class PrivateConversationImplementation implements PrivateConversationService {

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    PrivateConversationRepository privateConversationRepository;

    @Autowired
    PrivateMessageRepository privateMessageRepository;


    @Override
    public ResponseEntity<Object> checkprivatechat(Authentication authentication, long receiverId) {
        Client clientAuth = clientRepository.findByEmail(authentication.getName());
        Client receiverClient = clientRepository.findById(receiverId).get();
        if(clientAuth.getId() == receiverId){
            return new ResponseEntity<>("Unable to chat yourself", HttpStatus.NOT_ACCEPTABLE);
        }

        PrivateConversation privateConversation = privateConversationRepository.findByClientAndReceiverId(clientAuth, receiverClient.getId());

        if(privateConversation != null) {
            return new ResponseEntity<>("Chat found", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Chat not found", HttpStatus.FORBIDDEN);
        }

    }

    @Override
    public PrivateConversationDto getPrivateConversation(Authentication authentication, long receiverId) {
        Client clientAuth = clientRepository.findByEmail(authentication.getName());
        Client receiver = clientRepository.findById(receiverId).get();



        PrivateConversation privateConversation = privateConversationRepository.findByClientAndReceiverId(clientAuth, receiverId);


            return new PrivateConversationDto(privateConversation) ;



    }

    @Override
    public ResponseEntity<Object> makeprivatechat(Authentication authentication, long receiverId) {
        Client clientAuth = clientRepository.findByEmail(authentication.getName());
        Client receiverClient = clientRepository.findById(receiverId).get();

        if(clientAuth.getId() == receiverId){
            return new ResponseEntity<>("Unable to chat yourself", HttpStatus.NOT_ACCEPTABLE);
        } else {
            privateConversationRepository.save(new PrivateConversation(clientAuth, receiverClient.getId(), receiverClient.getNickName(), LocalDateTime.now()));

            privateConversationRepository.save(new PrivateConversation(receiverClient, clientAuth.getId(), clientAuth.getNickName(), LocalDateTime.now()));

            return new ResponseEntity<>("Chat created", HttpStatus.CREATED);
        }
    }





}
