package com.mycompany.redsocial.repositories;

import com.mycompany.redsocial.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Integer> {

    Optional<Post> findById(Integer integer);
    List<Post> findByIdGroupIsNull();

    // Obtener posts por idGroup con comentarios
    @Query("SELECT p FROM Post p LEFT JOIN FETCH p.comments WHERE p.idGroup.id = :idGroup")
    List<Post> findPostsByIdGroup(@Param("idGroup") Integer idGroup);

    // Obtener todos los posts con comentarios
    @Query("SELECT p FROM Post p LEFT JOIN FETCH p.comments")
    List<Post> findAllWithComments();

    // Obtener posts sin grupo con comentarios
    @Query("SELECT p FROM Post p LEFT JOIN FETCH p.comments WHERE p.idGroup IS NULL")
    List<Post> findPostsWithoutGroupWithComments();

    // Obtener un post por ID con comentarios
    @Query("SELECT p FROM Post p LEFT JOIN FETCH p.comments WHERE p.id = :id")
    Optional<Post> findPostByIdWithComments(@Param("id") Integer id);


}