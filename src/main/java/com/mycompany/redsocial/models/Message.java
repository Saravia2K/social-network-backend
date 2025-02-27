package com.mycompany.redsocial.models;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "message")
public class Message {
    @Id
    @Column(name = "id_message", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_usuario_from", nullable = false)
    private com.mycompany.redsocial.models.Usuario idUsuarioFrom;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_usuario_to", nullable = false)
    private com.mycompany.redsocial.models.Usuario idUsuarioTo;

    @Column(name = "date", nullable = false)
    private Instant date;

    @Lob
    @Column(name = "message", nullable = false)
    private String message;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public com.mycompany.redsocial.models.Usuario getIdUsuarioFrom() {
        return idUsuarioFrom;
    }

    public void setIdUsuarioFrom(com.mycompany.redsocial.models.Usuario idUsuarioFrom) {
        this.idUsuarioFrom = idUsuarioFrom;
    }

    public com.mycompany.redsocial.models.Usuario getIdUsuarioTo() {
        return idUsuarioTo;
    }

    public void setIdUsuarioTo(com.mycompany.redsocial.models.Usuario idUsuarioTo) {
        this.idUsuarioTo = idUsuarioTo;
    }

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}