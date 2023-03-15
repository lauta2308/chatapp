package com.chatapp.chatapp.services;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

public interface FriendService {


    Boolean checkfriend(Authentication authentication, Long friendId);

    ResponseEntity<Object> addFriend(Authentication authentication, Long friendId);
}
