package com.chatapp.chatapp.controllers;


import com.chatapp.chatapp.models.FriendStatus;
import com.chatapp.chatapp.services.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
public class FriendController {


    @Autowired
    FriendService friendService;

    @GetMapping("/api/clients/current/checkfriend")
    public FriendStatus checkfriend(Authentication authentication, @RequestParam Long friendId){

        return friendService.checkfriend(authentication, friendId);
    }

    @PostMapping("/api/clients/current/addfriend")
    public ResponseEntity<Object> addFriend(Authentication authentication, @RequestParam Long friendId) {

        return friendService.addFriend(authentication, friendId);

    }


    @DeleteMapping("/api/clients/current/removefriend")
    public ResponseEntity<Object> removeFriend(Authentication authentication, @RequestParam Long friendId) {

        return friendService.removeFriend(authentication, friendId);

    }

}
