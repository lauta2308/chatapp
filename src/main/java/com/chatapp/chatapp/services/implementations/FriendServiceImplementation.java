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

        Friend checkFriend = clientAuth.getFriends().stream().filter(friend -> friend.getFriendId() == friendId).findFirst().orElse(null);

        if(checkFriend != null){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public ResponseEntity<Object> addFriend(Authentication authentication, Long friendId) {
        Client clientAuth = clientRepository.findByEmail(authentication.getName());

        if(clientAuth.getId() == friendId){
            return new ResponseEntity<>("Unable to add yourself!", HttpStatus.FORBIDDEN);
        }

        Client clientFriend = clientRepository.findById(friendId).orElse(null);


        if(clientFriend == null){
            return new ResponseEntity<>("Client not found", HttpStatus.FORBIDDEN);
        }



        if(checkfriend(authentication, friendId) == true){
            return new ResponseEntity<>("Friend already added!", HttpStatus.FORBIDDEN);
        }



            Friend friend = new Friend(clientAuth,friendId, FriendStatus.FRIEND);
            friendRepository.save(friend);

            clientAuth.getFriends().add(friend);

            clientRepository.save(clientAuth);
            return new ResponseEntity<>("Added to friendlist!", HttpStatus.CREATED);

    }

    @Override
    public ResponseEntity<Object> removeFriend(Authentication authentication, Long friendId) {

        Client clientAuth = clientRepository.findByEmail(authentication.getName());



        if(checkfriend(authentication, friendId) == true){
            Friend friend = friendRepository.findByClientAndFriendId(clientAuth, friendId);
            friendRepository.delete(friend);
            return new ResponseEntity<>("Friend deleted!", HttpStatus.CREATED);

        } else {
            return new ResponseEntity<>("Friend not found", HttpStatus.FORBIDDEN);
        }
    }
}
