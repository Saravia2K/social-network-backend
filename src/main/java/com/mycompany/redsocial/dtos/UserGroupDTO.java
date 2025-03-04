package com.mycompany.redsocial.dtos;
import lombok.Data;
import java.time.LocalDate;

@Data
public class UserGroupDTO {
    private Integer idUsuario;
    private Integer idGroup;
    private String state;
}