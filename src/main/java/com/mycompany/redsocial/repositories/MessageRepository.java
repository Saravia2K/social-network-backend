package com.mycompany.redsocial.repositories;

import com.mycompany.redsocial.models.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {
    // Buscar mensajes enviados por un usuario
    @Query("SELECT m FROM Message m WHERE m.idUsuarioFrom.id = :userId")
    List<Message> findMessagesSentByUser(@Param("userId") Integer userId);

    // Buscar mensajes recibidos por un usuario
    @Query("SELECT m FROM Message m WHERE m.idUsuarioTo.id = :userId")
    List<Message> findMessagesReceivedByUser(@Param("userId") Integer userId);
}