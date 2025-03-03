package com.mycompany.redsocial.dtos;

import lombok.Data;

import java.time.Instant;

@Data
public class MessageDTO {
    private Integer idUsuarioFrom;
    private Integer idUsuarioTo;
    private String message;
    private Instant date;
}
