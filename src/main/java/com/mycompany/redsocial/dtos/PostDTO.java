package com.mycompany.redsocial.dtos;

import lombok.Data;
import java.time.Instant;

@Data
public class PostDTO {
    private Integer id;
    private Integer idUsuario;
    private Integer idGroup;
    private String subject;
    private String content;
    private Instant postDate;
    private String state;

}