package com.mycompany.redsocial.controllers;

import com.mycompany.redsocial.dtos.LoginDTO;
import com.mycompany.redsocial.dtos.LoginResponseDTO;
import com.mycompany.redsocial.dtos.RegistroDTO;
import com.mycompany.redsocial.dtos.UsuarioDTO;
import com.mycompany.redsocial.models.Usuario;
import com.mycompany.redsocial.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    // Endpoint para obtener todos los usuarios
    @GetMapping
    public List<Usuario> getAllUsuarios() {
        return usuarioService.getAllUsuarios();
    }
    // Registro de usuario
    @PostMapping("/registro")
    public ResponseEntity<Usuario> registrarUsuario(@RequestBody RegistroDTO registroDTO) {
        Usuario usuario = usuarioService.registrarUsuario(registroDTO);
        return ResponseEntity.ok(usuario);
    }

    // Login de usuario
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> loginUsuario(@RequestBody LoginDTO loginDTO) {
        LoginResponseDTO responseDTO = usuarioService.loginUsuario(loginDTO);
        return ResponseEntity.ok(responseDTO);
    }

    // Actualizar usuario
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> actualizarUsuario(@PathVariable Integer id, @RequestBody UsuarioDTO usuarioDTO) {
        Usuario usuario = usuarioService.actualizarUsuario(id, usuarioDTO);
        return ResponseEntity.ok(usuario);
    }
}