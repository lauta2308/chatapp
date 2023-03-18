package com.chatapp.chatapp.controllers;


import com.chatapp.chatapp.Dto.ClientDto;
import com.chatapp.chatapp.Dto.ChatClients;
import com.chatapp.chatapp.Dto.PrivateConversationDto;
import com.chatapp.chatapp.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class ClientController {
    @Autowired
    ClientService clientService;

    @GetMapping("/admin/clients")
    public List<ClientDto> getClients(){

        return clientService.findAll();
    }

    @GetMapping("/api/clients/{id}")
    public ClientDto getClient(@PathVariable Long id){


        return clientService.getClient(id);



    }

    @PostMapping("/api/register")

    public ResponseEntity<Object> register(

            @RequestParam String nickName, @RequestParam String email,

            @RequestParam String password) {


        return clientService.registerClient(nickName, email, password);


    }

    @PatchMapping("/api/clients/logged")
    public ResponseEntity<Object> clientLoggedStatus(Authentication authentication){

        return clientService.clientLoggedStatus(authentication);



    }

    @PatchMapping("/api/clients/logout")
    public ResponseEntity<Object> clientLogoutStatus(Authentication authentication){

        return clientService.clientLogoutStatus(authentication);



    }


    @GetMapping("/api/clients/current")

    public ClientDto getCurrentClient (Authentication authentication){

        return clientService.getCurrentClient(authentication);


    }




    @GetMapping("/api/clients/current/privatechats")

    public List<PrivateConversationDto> currentPrivateConversations (Authentication authentication){

        return clientService.getCurrentPrivateConversations(authentication);


    }



    @GetMapping("/api/clients/chatclients")

    public List<ChatClients> getOnlineClients (Authentication authentication){

        return clientService.getChatClients(authentication);


    }





    @GetMapping("/api/clients/filterclients")
    public List<ChatClients> filterClients(Authentication authentication, @RequestParam String nickName, @RequestParam  Boolean searchFriends){
        return clientService.filterOnlineClients(authentication, nickName, searchFriends);


    }




}

