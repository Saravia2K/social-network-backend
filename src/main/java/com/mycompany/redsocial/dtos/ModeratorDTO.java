package com.mycompany.redsocial.dtos;

import lombok.Data;

@Data // Lombok para generar getters, setters, toString, etc.
public class ModeratorDTO {
    private Integer id;
    private Integer idUsuario; // ID del usuario que es moderador
    private Integer idGroup;   // ID del grupo al que pertenece el moderador
}