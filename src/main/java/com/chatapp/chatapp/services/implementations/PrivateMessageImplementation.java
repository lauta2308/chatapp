package com.chatapp.chatapp.services.implementations;


import com.chatapp.chatapp.models.*;
import com.chatapp.chatapp.repositories.ClientRepository;
import com.chatapp.chatapp.repositories.PrivateConversationRepository;
import com.chatapp.chatapp.repositories.PrivateMessageRepository;
import com.chatapp.chatapp.services.PrivateMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PrivateMessageImplementation implements PrivateMessageService {

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    PrivateConversationRepository privateConversationRepository;

    @Autowired
    PrivateMessageRepository privateMessageRepository;

    @Override
    public ResponseEntity<Object> sendPrivateMessage(Authentication authentication, long receiverId, String message) {



        Client clientAuth= clientRepository.findByEmail(authentication.getName());
        Client receiverClient = clientRepository.findById(receiverId).orElse(null);

        if(clientAuth.equals(receiverClient)){
            return new ResponseEntity<>("Cant send message to yourself", HttpStatus.NOT_ACCEPTABLE);
        }

        PrivateConversation clientAuthtoReceiverChat = privateConversationRepository.findByClientAndReceiverId(clientAuth, receiverId);

        PrivateConversation receiverClientToAuthChat = privateConversationRepository.findByClientAndReceiverId(receiverClient, clientAuth.getId());

        if(clientAuthtoReceiverChat == null || receiverClientToAuthChat == null){
            return new ResponseEntity<>("Chat not found", HttpStatus.FORBIDDEN);
        }

        clientAuthtoReceiverChat.setLastChange(LocalDateTime.now());
        receiverClientToAuthChat.setLastChange(LocalDateTime.now());
        PrivateMessage client1to2message = new PrivateMessage(LocalDateTime.now(), PrivateMessageType.SENDER, PrivateMessageStatus.READED, message);

        PrivateMessage client2to1message = new PrivateMessage(LocalDateTime.now(), PrivateMessageType.RECEIVER,PrivateMessageStatus.NOT_READED, message);


        client1to2message.setPrivateConversation(clientAuthtoReceiverChat);
        client2to1message.setPrivateConversation(receiverClientToAuthChat);

        privateMessageRepository.save(client1to2message);
        privateMessageRepository.save(client2to1message);

        privateConversationRepository.save(clientAuthtoReceiverChat);
        privateConversationRepository.save(receiverClientToAuthChat);
        return new ResponseEntity<>("Message sent", HttpStatus.CREATED);





    }

    @Override
    public ResponseEntity<Object> readPrivateMessages(Authentication authentication, long receiverId) {

        Client clientAuth= clientRepository.findByEmail(authentication.getName());

        PrivateConversation clientAuthtoReceiverChat = privateConversationRepository.findByClientAndReceiverId(clientAuth, receiverId);


        clientAuthtoReceiverChat.getMessages().stream().forEach(privateMessage -> privateMessage.setMessageStatus(PrivateMessageStatus.READED));

        privateConversationRepository.save(clientAuthtoReceiverChat);

        return new ResponseEntity<>("Messages readed", HttpStatus.CREATED);


    }


}
