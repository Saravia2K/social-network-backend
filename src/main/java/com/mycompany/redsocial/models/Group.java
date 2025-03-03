package com.mycompany.redsocial.models;

import jakarta.persistence.*;

@Entity
@Table(name = "`group`")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_group", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Lob
    @Column(name = "description")
    private String description;

    @Column(name = "id_usuario_creator") // Nuevo campo
    private Integer idUsuarioCreator; // ID del usuario creador

    public Integer getIdUsuarioCreator() {
        return idUsuarioCreator;
    }

    public void setIdUsuarioCreator(Integer idUsuarioCreator) {
        this.idUsuarioCreator = idUsuarioCreator;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}