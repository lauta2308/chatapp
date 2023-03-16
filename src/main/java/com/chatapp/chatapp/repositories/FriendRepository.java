package com.chatapp.chatapp.repositories;

import com.chatapp.chatapp.models.Client;
import com.chatapp.chatapp.models.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface FriendRepository extends JpaRepository<Friend, Long> {

    Friend findByClientAndFriendId(Client client, long receiverId);
}
