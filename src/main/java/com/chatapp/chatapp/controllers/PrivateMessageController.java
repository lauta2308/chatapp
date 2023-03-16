package com.chatapp.chatapp.controllers;


import com.chatapp.chatapp.services.PrivateMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PrivateMessageController {

    @Autowired
    PrivateMessageService privateMessageService;


    @PostMapping("/api/clients/current/privatemessage")
    public ResponseEntity<Object> sendprivatemessage(Authentication authentication, @RequestParam long receiverId, @RequestParam String message){

        return privateMessageService.sendPrivateMessage(authentication,receiverId, message);
    }


    @PatchMapping("/api/clients/current/readprivatemessages")
    public ResponseEntity<Object> readPrivateMessages(Authentication authentication, @RequestParam long receiverId){

        return privateMessageService.readPrivateMessages(authentication,receiverId);
    }
}
