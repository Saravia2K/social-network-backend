package com.mycompany.redsocial.controllers;

import com.mycompany.redsocial.dtos.MessageDTO;
import com.mycompany.redsocial.models.Message;
import com.mycompany.redsocial.models.Usuario;
import com.mycompany.redsocial.services.MessageService;
import com.mycompany.redsocial.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public Message createMessage(@RequestBody MessageDTO messageDTO) {
        Usuario fromUser = usuarioService.getUsuarioById(messageDTO.getIdUsuarioFrom())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        Usuario toUser = usuarioService.getUsuarioById(messageDTO.getIdUsuarioTo())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Message message = new Message();
        message.setIdUsuarioFrom(fromUser);
        message.setIdUsuarioTo(toUser);
        message.setMessage(messageDTO.getMessage());
        message.setDate(messageDTO.getDate() != null ? messageDTO.getDate() : Instant.now());

        return messageService.createMessage(message);
    }

    @GetMapping
    public List<MessageDTO> getAllMessages() {
        return messageService.getAllMessages().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MessageDTO> getMessageById(@PathVariable Integer id) {
        return messageService.getMessageById(id)
                .map(this::convertToDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<MessageDTO> updateMessage(@PathVariable Integer id, @RequestBody MessageDTO messageDetails) {
        Usuario fromUser = usuarioService.getUsuarioById(messageDetails.getIdUsuarioFrom())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        Usuario toUser = usuarioService.getUsuarioById(messageDetails.getIdUsuarioTo())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Message updatedMessage = new Message();
        updatedMessage.setId(id); // Asegúrate de establecer el ID del mensaje a actualizar
        updatedMessage.setIdUsuarioFrom(fromUser);
        updatedMessage.setIdUsuarioTo(toUser);
        updatedMessage.setMessage(messageDetails.getMessage());
        updatedMessage.setDate(messageDetails.getDate() != null ? messageDetails.getDate() : Instant.now());

        try {
            Message updated = messageService.updateMessage(id, updatedMessage);
            return ResponseEntity.ok(convertToDTO(updated));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    private MessageDTO convertToDTO(Message message) {
        MessageDTO dto = new MessageDTO();
        dto.setIdUsuarioFrom(message.getIdUsuarioFrom().getId());
        dto.setIdUsuarioTo(message.getIdUsuarioTo().getId());
        dto.setMessage(message.getMessage());
        dto.setDate(message.getDate());
        return dto;
    }
}
