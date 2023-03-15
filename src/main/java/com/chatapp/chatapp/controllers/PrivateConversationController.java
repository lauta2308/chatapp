package com.chatapp.chatapp.controllers;

import com.chatapp.chatapp.Dto.PrivateConversationDto;
import com.chatapp.chatapp.services.PrivateConversationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PrivateConversationController {

    @Autowired
    PrivateConversationService privateConversationService;


    @GetMapping("/api/clients/current/checkprivatechat")
    public ResponseEntity<Object> checkprivatechat(Authentication authentication, @RequestParam long receiverId){

        return privateConversationService.checkprivatechat(authentication,receiverId);
    }


    @GetMapping("/api/clients/current/getprivatechat")

    public PrivateConversationDto getPrivateConversation (Authentication authentication, @RequestParam long receiverId){

        return privateConversationService.getPrivateConversation(authentication, receiverId);


    }

    @PostMapping("/api/clients/current/privatechat")
        public ResponseEntity<Object> makeprivatechat(Authentication authentication, @RequestParam long receiverId){

            return privateConversationService.makeprivatechat(authentication,receiverId);
        }


    @PostMapping("/api/clients/current/privatemessage")
    public ResponseEntity<Object> sendprivatemessage(Authentication authentication, @RequestParam long receiverId,  @RequestParam String message){

        return privateConversationService.sendPrivateMessage(authentication,receiverId, message);
    }
}
