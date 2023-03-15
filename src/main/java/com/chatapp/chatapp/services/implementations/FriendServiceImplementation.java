package com.chatapp.chatapp.services.implementations;


import com.chatapp.chatapp.models.Client;
import com.chatapp.chatapp.models.Friend;
import com.chatapp.chatapp.models.FriendStatus;
import com.chatapp.chatapp.repositories.ClientRepository;
import com.chatapp.chatapp.repositories.FriendRepository;
import com.chatapp.chatapp.services.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class FriendServiceImplementation implements FriendService {
    @Autowired
    ClientRepository clientRepository;

    @Autowired
    FriendRepository friendRepository;
    @Override
    public Boolean checkfriend(Authentication authentication, Long friendId) {

        Client clientAuth = clientRepository.findByEmail(authentication.getName());

        if(clientAuth.getId() == friendId){
            return false;
        }

        Set<Friend> clientAuthFriends = clientAuth.getFriends();

        Boolean checkFriend = clientAuthFriends.stream().filter(friend -> friend.getId() == friendId).findFirst().isPresent();

        if(checkFriend == true){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public ResponseEntity<Object> addFriend(Authentication authentication, Long friendId) {
        Client clientAuth = clientRepository.findByEmail(authentication.getName());


        if(clientAuth.getId() == friendId){
            return new ResponseEntity<>("Cant add yourself as friend", HttpStatus.FORBIDDEN);
        }

        long clientFriend = clientRepository.findById(friendId).stream().count();

        if(clientFriend == 0){
            return new ResponseEntity<>("Friend id not valid", HttpStatus.FORBIDDEN);
        }

        Set<Friend> clientFriends = clientAuth.getFriends();

        long friendAlreadyExists = clientFriends.stream().filter(friend -> friend.getId() == friendId).count();

        if(friendAlreadyExists > 0){
            return new ResponseEntity<>("Friend already added!", HttpStatus.FORBIDDEN);
        }

        Client friend = clientRepository.findById(friendId).get();

        Friend friend2to1 = new Friend(friend, FriendStatus.FRIEND);
        clientAuth.getFriends().add(friend2to1);
        friendRepository.save(friend2to1);

        Friend friend1to2 = new Friend(clientAuth, FriendStatus.FRIEND);
        friend.getFriends().add(friend1to2);
        friendRepository.save(friend1to2);
        clientRepository.save(clientAuth);
        clientRepository.save(friend);

        return new ResponseEntity<>("Added to friendlist!", HttpStatus.CREATED);
    }
}
