package com.mycompany.redsocial.models;

import jakarta.persistence.*;

@Entity
@Table(name = "moderator")
public class Moderator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_moderator", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_usuario", nullable = false)
    private com.mycompany.redsocial.models.Usuario idUsuario;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_group", nullable = false)
    private com.mycompany.redsocial.models.Group idGroup;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public com.mycompany.redsocial.models.Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(com.mycompany.redsocial.models.Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    public com.mycompany.redsocial.models.Group getIdGroup() {
        return idGroup;
    }

    public void setIdGroup(com.mycompany.redsocial.models.Group idGroup) {
        this.idGroup = idGroup;
    }

}