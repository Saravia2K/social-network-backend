package com.mycompany.redsocial.controllers;

import com.mycompany.redsocial.dtos.PostDTO;
import com.mycompany.redsocial.models.Post;
import com.mycompany.redsocial.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostService postService;

    // Crear un nuevo post
    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody PostDTO postDTO) {
        Post post = postService.createPost(postDTO);
        return new ResponseEntity<>(post, HttpStatus.CREATED);
    }

    // Endpoint para obtener posts donde idGroup es null
    @GetMapping("/without-group")
    public ResponseEntity<List<PostDTO>> getPostsWithoutGroup() {
        List<PostDTO> posts = postService.getPostsWithoutGroup();
        return ResponseEntity.ok(posts);
    }

    // Obtener un post por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable Integer id) {
        Post post = postService.getPostById(id);
        return ResponseEntity.ok(post);
    }

    // Eliminar un post por su ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Integer id) {
        postService.deletePost(id);
        return ResponseEntity.noContent().build();
    }
}