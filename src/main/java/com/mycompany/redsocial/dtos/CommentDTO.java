
package com.mycompany.redsocial.dtos;

import lombok.Data;

@Data
public class CommentDTO {
    private Integer idUsuario;
    private Integer idPost;
    private String comment;
}