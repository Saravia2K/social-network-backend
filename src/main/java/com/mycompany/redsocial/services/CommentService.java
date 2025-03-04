package com.mycompany.redsocial.services;

import com.mycompany.redsocial.dtos.CommentDTO;
import com.mycompany.redsocial.models.Comment;
import com.mycompany.redsocial.models.Usuario;
import com.mycompany.redsocial.models.Post;
import com.mycompany.redsocial.repositories.CommentRepository;
import com.mycompany.redsocial.repositories.UsuarioRepository;
import com.mycompany.redsocial.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PostRepository postRepository;

    // Crear un comentario
    public Comment createComment(CommentDTO commentDTO) {
        Comment comment = new Comment();

        // Buscar y asignar Usuario
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(commentDTO.getIdUsuario());
        usuarioOptional.ifPresent(comment::setIdUsuario);

        // Buscar y asignar Post
        Optional<Post> postOptional = postRepository.findById(commentDTO.getIdPost());
        postOptional.ifPresent(comment::setIdPost);

        // Asignar el comentario
        comment.setComment(commentDTO.getComment());

        // Asignar la fecha actual
        comment.setDate(Instant.now());

        return commentRepository.save(comment);
    }

    // Obtener un comentario por ID
    public Optional<Comment> getCommentById(Integer id) {
        return commentRepository.findById(id);
    }

    // Obtener todos los comentarios
    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    // Actualizar un comentario
    public Comment updateComment(Integer id, CommentDTO commentDTO) {
        Optional<Comment> commentOptional = commentRepository.findById(id);
        if (commentOptional.isPresent()) {
            Comment comment = commentOptional.get();

            // Actualizar el comentario
            comment.setComment(commentDTO.getComment());

            // Actualizar Usuario y Post si es necesario
            Optional<Usuario> usuarioOptional = usuarioRepository.findById(commentDTO.getIdUsuario());
            usuarioOptional.ifPresent(comment::setIdUsuario);

            Optional<Post> postOptional = postRepository.findById(commentDTO.getIdPost());
            postOptional.ifPresent(comment::setIdPost);

            return commentRepository.save(comment);
        }
        return null;
    }

    // Eliminar un comentario
    public void deleteComment(Integer id) {
        commentRepository.deleteById(id);
    }

    // Obtener comentarios por idPost
    public List<Comment> getCommentsByPostId(Integer idPost) {
        return commentRepository.findByIdPost_Id(idPost); // Cambiado a findByIdPost_Id
    }
}