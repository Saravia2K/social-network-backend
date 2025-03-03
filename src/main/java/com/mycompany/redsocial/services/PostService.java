package com.mycompany.redsocial.services;

import com.mycompany.redsocial.dtos.PostDTO;
import com.mycompany.redsocial.models.Post;
import com.mycompany.redsocial.models.Usuario;
import com.mycompany.redsocial.models.Group;
import com.mycompany.redsocial.repositories.PostRepository;
import com.mycompany.redsocial.repositories.UsuarioRepository;
import com.mycompany.redsocial.repositories.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private GroupRepository groupRepository;

    // Método para crear un post
    public Post createPost(PostDTO postDTO) {
        Post post = new Post();
        post.setSubject(postDTO.getSubject());
        post.setContent(postDTO.getContent());
        post.setPostDate(postDTO.getPostDate() != null ? postDTO.getPostDate() : Instant.now());
        post.setState(postDTO.getState());

        // Obtener el Usuario por su ID
        Usuario usuario = usuarioRepository.findById(postDTO.getIdUsuario())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        post.setIdUsuario(usuario);

        // Obtener el Group por su ID (si está presente)
        if (postDTO.getIdGroup() != null) {
            Group group = groupRepository.findById(postDTO.getIdGroup())
                    .orElseThrow(() -> new RuntimeException("Grupo no encontrado"));
            post.setIdGroup(group);
        }

        return postRepository.save(post);
    }

    public List<PostDTO> getPostsWithoutGroup() {
        List<Post> posts = postRepository.findByIdGroupIsNull();
        return posts.stream()
                .map(post -> {
                    PostDTO postDTO = new PostDTO();
                    postDTO.setId(post.getId());
                    postDTO.setIdUsuario(post.getIdUsuario().getId());
                    postDTO.setIdGroup(post.getIdGroup() != null ? post.getIdGroup().getId() : null);
                    postDTO.setSubject(post.getSubject());
                    postDTO.setContent(post.getContent());
                    postDTO.setPostDate(post.getPostDate());
                    postDTO.setState(post.getState());
                    return postDTO;
                })
                .collect(Collectors.toList());
    }

    // Método para obtener un post por su ID
    public Post getPostById(Integer id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post no encontrado"));
    }

    // Método para eliminar un post por su ID
    public void deletePost(Integer id) {
        postRepository.deleteById(id);
    }
}