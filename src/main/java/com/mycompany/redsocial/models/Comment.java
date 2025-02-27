package com.mycompany.redsocial.models;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_comment", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_usuario", nullable = false)
    private com.mycompany.redsocial.models.Usuario idUsuario;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_post", nullable = false)
    private com.mycompany.redsocial.models.Post idPost;

    @Lob
    @Column(name = "comment", nullable = false)
    private String comment;

    @Column(name = "date", nullable = false)
    private Instant date;

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

    public com.mycompany.redsocial.models.Post getIdPost() {
        return idPost;
    }

    public void setIdPost(com.mycompany.redsocial.models.Post idPost) {
        this.idPost = idPost;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

}