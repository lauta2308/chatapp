package com.chatapp.chatapp;

import com.chatapp.chatapp.models.*;
import com.chatapp.chatapp.repositories.*;
import com.chatapp.chatapp.repositories.FriendRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

@SpringBootApplication
public class ChatappApplication {

	@Autowired
	private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(ChatappApplication.class, args);
	}


	@Bean
	public CommandLineRunner initData(ClientRepository clientRepository, GeneralChatRepository generalChatRepository, FriendRepository friendRepository, PrivateConversationRepository privateConversationRepository, PrivateMessageRepository privateMessageRepository) {
		return (args) -> {

			Client client1 = new Client("Lauta", "lauta@test.com", passwordEncoder.encode("123"));

			client1.setClientStatus(ClientStatus.OFFLINE);
			clientRepository.save(client1);



			Client client2 = new Client("Lauta2", "lauta@testing.com", passwordEncoder.encode("1234"));
			client2.setClientStatus(ClientStatus.OFFLINE);
			clientRepository.save(client2);



			Client client3 = new Client("Lauta3", "lauta@testing2.com", passwordEncoder.encode("12345"));
			client3.setClientStatus(ClientStatus.OFFLINE);
			clientRepository.save(client3);




			Client admin1 = new Client("admin", "admin@test.com", passwordEncoder.encode("1234"));
			admin1.setClientStatus(ClientStatus.OFFLINE);

			clientRepository.save(admin1);

			Client admin2 = new Client("admin2", "admin@test2.com", passwordEncoder.encode("1234"));
			admin2.setClientStatus(ClientStatus.OFFLINE);
			clientRepository.save(admin2);

			Client client4 = new Client("client4", "client@test4.com", passwordEncoder.encode("1234"));
			client4.setClientStatus(ClientStatus.OFFLINE);
			clientRepository.save(client4);


			Client client5 = new Client("Lauta2", "lauta@testing5.com", passwordEncoder.encode("12345"));
			client5.setClientStatus(ClientStatus.OFFLINE);
			clientRepository.save(client5);

			GeneralChat generalChat1 = new GeneralChat("first message!", LocalDateTime.now(),  client1);

			generalChatRepository.save(generalChat1);

			GeneralChat generalChat2 = new GeneralChat("hi", LocalDateTime.now(),  client1);

			generalChatRepository.save(generalChat2);

			GeneralChat generalChat3 = new GeneralChat("all good", LocalDateTime.now(),  client2);

			generalChatRepository.save(generalChat3);


			Friend friend2to1 = new Friend(client2, FriendStatus.FRIEND);
			client1.getFriends().add(friend2to1);
			friendRepository.save(friend2to1);

			Friend friend1to2 = new Friend(client1, FriendStatus.FRIEND);
			client2.getFriends().add(friend1to2);
			friendRepository.save(friend1to2);
			clientRepository.save(client1);
			clientRepository.save(client2);




			PrivateConversation client1to2newchat = new PrivateConversation(client1, client2.getId(), client2.getNickName());
			privateConversationRepository.save(client1to2newchat);

			PrivateConversation client2to1newchat = new PrivateConversation(client2, client1.getId(), client1.getNickName());
			privateConversationRepository.save(client2to1newchat);


			PrivateConversation client1to2chat = privateConversationRepository.findByClientAndReceiverId(client1, client2.getId());

			PrivateConversation client2to1chat = privateConversationRepository.findByClientAndReceiverId(client2, client1.getId());

			PrivateMessage client1to2message = new PrivateMessage(LocalDateTime.now(), PrivateMessageType.SENDER, "message from client 1 to client 2");

			PrivateMessage client2to1message = new PrivateMessage(LocalDateTime.now(), PrivateMessageType.RECEIVER, "message from client 1 to client 2");

			client1to2message.setPrivateConversation(client1to2chat);
			client2to1message.setPrivateConversation(client2to1chat);

			client1to2chat.setLastChange(LocalDateTime.now());
			client2to1chat.setLastChange(LocalDateTime.now());


			privateMessageRepository.save(client1to2message);
			privateMessageRepository.save(client2to1message);

			privateConversationRepository.save(client1to2chat);
			privateConversationRepository.save(client2to1chat);



			PrivateConversation client1to3newchat = new PrivateConversation(client1, client3.getId(), client3.getNickName());
			privateConversationRepository.save(client1to3newchat);

			PrivateConversation client3to1newchat = new PrivateConversation(client3, client1.getId(), client1.getNickName());
			privateConversationRepository.save(client3to1newchat);


			PrivateConversation client1to3chat = privateConversationRepository.findByClientAndReceiverId(client1, client3.getId());

			PrivateConversation client3to1chat = privateConversationRepository.findByClientAndReceiverId(client3, client1.getId());

			PrivateMessage client1to3message = new PrivateMessage(LocalDateTime.now(), PrivateMessageType.SENDER, "message from client 1 to client 3");

			PrivateMessage client3to1message = new PrivateMessage(LocalDateTime.now(), PrivateMessageType.RECEIVER, "message from client 1 to client 3");


			client1to3message.setPrivateConversation(client1to3chat);
			client3to1message.setPrivateConversation(client3to1chat);

			client1to3chat.setLastChange(LocalDateTime.now());
			client3to1chat.setLastChange(LocalDateTime.now());


			privateMessageRepository.save(client1to3message);
			privateMessageRepository.save(client3to1message);

			privateConversationRepository.save(client1to3chat);
			privateConversationRepository.save(client3to1chat);





		};





		}






}
