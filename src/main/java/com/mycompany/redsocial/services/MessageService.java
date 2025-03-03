package com.mycompany.redsocial.services;

import com.mycompany.redsocial.models.Message;
import com.mycompany.redsocial.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;


    public Message createMessage(Message message) {
        message.setDate(Instant.now());
        return messageRepository.save(message);
    }


    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }


    public Optional<Message> getMessageById(Integer id) {
        return messageRepository.findById(id);
    }


    public Message updateMessage(Integer id, Message messageDetails) {
        Optional<Message> optionalMessage = messageRepository.findById(id);
        if (optionalMessage.isPresent()) {
            Message message = optionalMessage.get();
            message.setIdUsuarioFrom(messageDetails.getIdUsuarioFrom());
            message.setIdUsuarioTo(messageDetails.getIdUsuarioTo());
            message.setMessage(messageDetails.getMessage());
            message.setDate(Instant.now());
            return messageRepository.save(message);
        } else {
            throw new RuntimeException("no hay mensajes con el id: " + id);
        }
    }
}