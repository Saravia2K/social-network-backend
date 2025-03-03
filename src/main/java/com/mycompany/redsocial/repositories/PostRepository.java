package com.mycompany.redsocial.repositories;

import com.mycompany.redsocial.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Integer> {

    Optional<Post> findById(Integer integer);
    List<Post> findByIdGroupIsNull();



}