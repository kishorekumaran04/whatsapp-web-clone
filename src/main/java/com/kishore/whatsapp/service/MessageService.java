package com.kishore.whatsapp.service;

import java.util.List;

import com.kishore.whatsapp.exception.ChatException;
import com.kishore.whatsapp.exception.MessageException;
import com.kishore.whatsapp.exception.UserException;
import com.kishore.whatsapp.modal.Message;
import com.kishore.whatsapp.modal.User;
import com.kishore.whatsapp.request.SendMessageRequest;

public interface MessageService {
	
	public Message sendMessage(SendMessageRequest req) throws UserException, ChatException;
	
	public List<Message> getChatMessages(Integer chatId, User reqUser) throws ChatException, UserException;
	
	public Message findMessageById(Integer messageId) throws MessageException;
	
	public void deleteMessage(Integer messageId, User reqUser) throws MessageException, UserException;
	
}
