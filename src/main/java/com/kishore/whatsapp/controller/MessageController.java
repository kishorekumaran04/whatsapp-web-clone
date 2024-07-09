package com.kishore.whatsapp.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kishore.whatsapp.exception.ChatException;
import com.kishore.whatsapp.exception.MessageException;
import com.kishore.whatsapp.exception.UserException;
import com.kishore.whatsapp.modal.Message;
import com.kishore.whatsapp.modal.User;
import com.kishore.whatsapp.request.SendMessageRequest;
import com.kishore.whatsapp.response.ApiResponse;
import com.kishore.whatsapp.service.MessageService;
import com.kishore.whatsapp.service.UserService;

@RestController
@RequestMapping("/api/messages")
public class MessageController {
	
	private MessageService messageService;
	private UserService userService;
	
	public MessageController(MessageService messageService, UserService userService) {
		this.messageService = messageService;
		this.userService = userService;
	}
	
	@PostMapping("/create")
	public ResponseEntity<Message> sendMessageHandler(@RequestBody SendMessageRequest req, @RequestHeader("Authorization") String jwt) throws UserException, ChatException {
		
		User user  = userService.findUserProfile(jwt);
		req.setUserId(user.getId());
		
		Message message = messageService.sendMessage(req);
		
		return new ResponseEntity<Message>(message, HttpStatus.OK);
	}
	
	@GetMapping("/chat/{chatId}")
	public ResponseEntity<List<Message>> getChatMessagesHandler(@PathVariable("chatId") Integer chatId, @RequestHeader("Authorization") String jwt) throws UserException, ChatException {
		
		User user  = userService.findUserProfile(jwt);
		
		List<Message> messages = messageService.getChatMessages(chatId, user);
		
		return new ResponseEntity<>(messages , HttpStatus.OK);
	}
	
	@DeleteMapping("/{messageId}")
	public ResponseEntity<ApiResponse> deleteMessagesHandler(@PathVariable("messageId") Integer messageId, @RequestHeader("Authorization") String jwt) throws MessageException, UserException {
		
		User user  = userService.findUserProfile(jwt);
		
		messageService.deleteMessage(messageId, user);
		
		ApiResponse res = new ApiResponse("Message deelted successfully", false);
		
		return new ResponseEntity<>(res , HttpStatus.OK);
	}
	
	

}
