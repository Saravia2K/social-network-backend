package com.mycompany.redsocial.dtos;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UsuarioDTO {
    private Integer id;
    private String username;
    private String email;
    private String cellphone;
    private String password;
    private String webPage;
    private String address;
    private LocalDate birthDate;
    private String profilePic;
    private String description;
}