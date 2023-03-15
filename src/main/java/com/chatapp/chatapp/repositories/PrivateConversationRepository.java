package com.chatapp.chatapp.repositories;

import com.chatapp.chatapp.models.Client;
import com.chatapp.chatapp.models.PrivateConversation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface PrivateConversationRepository extends JpaRepository<PrivateConversation, Long> {
    PrivateConversation findById(long id);
    PrivateConversation findByClientAndReceiverId(Client client, long receiverId);


}
