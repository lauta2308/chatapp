package com.chatapp.chatapp.services;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestParam;

public interface PrivateMessageService {


    ResponseEntity<Object> sendPrivateMessage(Authentication authentication, @RequestParam long receiverId, @RequestParam String message);

    ResponseEntity<Object> readPrivateMessages(Authentication authentication, @RequestParam long receiverId);






}
