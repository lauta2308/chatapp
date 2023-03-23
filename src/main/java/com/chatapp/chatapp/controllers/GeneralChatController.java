package com.chatapp.chatapp.controllers;

import com.chatapp.chatapp.Dto.GeneralChatDto;
import com.chatapp.chatapp.models.MessageColor;
import com.chatapp.chatapp.services.GeneralChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GeneralChatController {

    @Autowired
    GeneralChatService generalChatService;

    @GetMapping("/api/general")
    public List<GeneralChatDto> getGeneralChat(){

        return generalChatService.findAll();
    }


    @PostMapping("/api/general")
    public ResponseEntity<Object> addGeneralMessage(Authentication authentication, @RequestParam String message, @RequestParam MessageColor messageColor){

        return generalChatService.addMessage(authentication, message, messageColor);
    }
}
