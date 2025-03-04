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
    @Query("SELECT p FROM Post p WHERE p.idGroup.id = :idGroup")
    List<Post> findPostsByIdGroup(@Param("idGroup") Integer idGroup);


}