package com.mycompany.redsocial.models;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "post")
public class Post {
    @Id
    @Column(name = "id_post", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_usuario", nullable = false)
    private com.mycompany.redsocial.models.Usuario idUsuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_group")
    private com.mycompany.redsocial.models.Group idGroup;

    @Column(name = "subject", nullable = false)
    private String subject;

    @Lob
    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "post_date")
    private Instant postDate;

    @Column(name = "state", length = 20)
    private String state;

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

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Instant getPostDate() {
        return postDate;
    }

    public void setPostDate(Instant postDate) {
        this.postDate = postDate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

}