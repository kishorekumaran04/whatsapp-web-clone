package com.kishore.whatsapp.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.kishore.whatsapp.exception.ChatException;
import com.kishore.whatsapp.exception.MessageException;
import com.kishore.whatsapp.exception.UserException;
import com.kishore.whatsapp.modal.Chat;
import com.kishore.whatsapp.modal.Message;
import com.kishore.whatsapp.modal.User;
import com.kishore.whatsapp.repository.MessageRepository;
import com.kishore.whatsapp.request.SendMessageRequest;

@Service
public class MessageServiceImpl implements MessageService {

	private MessageRepository messageRepository;
	private UserService userService;
	private ChatService chatService;
	
	public MessageServiceImpl(MessageRepository messageRepository, UserService userService, ChatService chatService) {
		this.messageRepository = messageRepository;
		this.userService = userService;
		this.chatService = chatService;
	}
	
	@Override
	public Message sendMessage(SendMessageRequest req) throws UserException, ChatException {
		User user = userService.findUserById(req.getUserId());
		Chat chat = chatService.findChatById(req.getChatId());
		
		Message message = new Message();
		message.setChat(chat);
		message.setUser(user);
		message.setContent(req.getContent());
		message.setTimeStamp(LocalDateTime.now());
		
		return message;
	}

	@Override
	public List<Message> getChatMessages(Integer chatId, User reqUser) throws ChatException, UserException {
		Chat chat = chatService.findChatById(chatId);
		
		if(!chat.getUsers().contains(reqUser))
			throw new UserException("Unauthorized access for chat " + chat.getId());		
		
		List<Message> messages = messageRepository.findChatById(chat.getId());
		return messages;
	}

	@Override
	public Message findMessageById(Integer messageId) throws MessageException {
		
		Optional<Message> opt = messageRepository.findById(messageId);
		
		if(opt.isPresent()) {
			return opt.get();
		}
		
		throw new MessageException("Message not found with id " + messageId);
	}

	@Override
	public void deleteMessage(Integer messageId, User reqUser) throws MessageException, UserException {
		
		Message message = findMessageById(messageId);
		
		if(message.getUser().getId().equals(reqUser.getId())) {
			messageRepository.deleteById(messageId);
		}
		
		throw new UserException("You cannot delete another user's message " + reqUser.getFullName());
		
		
	}

}
