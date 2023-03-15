package com.chatapp.chatapp.repositories;

import com.chatapp.chatapp.models.GeneralChat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface GeneralChatRepository extends JpaRepository<GeneralChat, Long> {
}
