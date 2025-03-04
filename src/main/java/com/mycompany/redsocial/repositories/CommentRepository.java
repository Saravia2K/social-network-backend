package com.mycompany.redsocial.repositories;

import com.mycompany.redsocial.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
    // Buscar comentarios por idPost
    List<Comment> findByIdPost_Id(Integer idPost);
}