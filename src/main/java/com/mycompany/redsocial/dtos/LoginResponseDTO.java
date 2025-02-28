package com.mycompany.redsocial.dtos;

import lombok.Data;

import java.time.LocalDate;

@Data
public class LoginResponseDTO {
    private Integer id;
    private String username;
    private String email;
    private String cellphone;
    private String webPage;
    private String address;
    private LocalDate birthDate;
    private String profilePic;
    private String description;
}