package com.chatapp.chatapp.controllers;


import com.chatapp.chatapp.Dto.ClientDto;
import com.chatapp.chatapp.services.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FriendController {


    @Autowired
    FriendService friendService;

    @GetMapping("/api/clients/current/checkfriend")
    public Boolean checkfriend(Authentication authentication, @RequestParam Long friendId){

        return friendService.checkfriend(authentication, friendId);
    }

    @PostMapping("/api/clients/current/addfriend")
    public ResponseEntity<Object> addFriend(Authentication authentication, @RequestParam Long friendId) {

        return friendService.addFriend(authentication, friendId);

    }
}
