package com.mycompany.redsocial.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "user_group")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) // Ignorar proxies de Hibernate
public class UserGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user_group", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_usuario", nullable = false)
    private com.mycompany.redsocial.models.Usuario idUsuario;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_group", nullable = false)
    private com.mycompany.redsocial.models.Group idGroup;

    @Column(name = "state", nullable = false, length = 20)
    private String state;

    @Column(name = "join_date")
    private LocalDate joinDate;

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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public LocalDate getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(LocalDate joinDate) {
        this.joinDate = joinDate;
    }

}