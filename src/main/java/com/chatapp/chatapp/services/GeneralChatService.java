package com.chatapp.chatapp.services;

import com.chatapp.chatapp.Dto.GeneralChatDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface GeneralChatService {

    List<GeneralChatDto> findAll();


    ResponseEntity<Object> addMessage(Authentication authentication, String message);
}
