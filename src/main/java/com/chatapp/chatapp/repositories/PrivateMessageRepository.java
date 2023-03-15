package com.chatapp.chatapp.repositories;


import com.chatapp.chatapp.models.PrivateMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface PrivateMessageRepository extends JpaRepository<PrivateMessage, Long> {
}
