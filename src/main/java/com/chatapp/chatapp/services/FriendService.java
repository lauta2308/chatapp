package com.chatapp.chatapp.services;

import com.chatapp.chatapp.models.FriendStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestParam;

public interface FriendService {


    FriendStatus checkfriend(Authentication authentication, Long friendId);

    ResponseEntity<Object> addFriend(Authentication authentication, Long friendId);

    ResponseEntity<Object> removeFriend(Authentication authentication, @RequestParam Long friendId);
}
