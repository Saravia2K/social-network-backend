package com.mycompany.redsocial.dtos;

import lombok.Data;

@Data
public class GroupDTO {
    private Integer id;
    private String name;
    private String description;
    private Integer idUsuarioCreator;

}
