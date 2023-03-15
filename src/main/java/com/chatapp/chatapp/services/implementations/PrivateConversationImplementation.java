package com.chatapp.chatapp.services.implementations;


import com.chatapp.chatapp.Dto.PrivateConversationDto;
import com.chatapp.chatapp.models.Client;
import com.chatapp.chatapp.models.PrivateConversation;
import com.chatapp.chatapp.models.PrivateMessage;
import com.chatapp.chatapp.models.PrivateMessageType;
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
        PrivateMessage client1to2message = new PrivateMessage(LocalDateTime.now(), PrivateMessageType.SENDER, message);

        PrivateMessage client2to1message = new PrivateMessage(LocalDateTime.now(), PrivateMessageType.RECEIVER, message);


            client1to2message.setPrivateConversation(clientAuthtoReceiverChat);
            client2to1message.setPrivateConversation(receiverClientToAuthChat);

            privateMessageRepository.save(client1to2message);
            privateMessageRepository.save(client2to1message);

            privateConversationRepository.save(clientAuthtoReceiverChat);
            privateConversationRepository.save(receiverClientToAuthChat);
            return new ResponseEntity<>("Message sent", HttpStatus.CREATED);





    }


}
