package com.mycompany.redsocial.services;

import com.mycompany.redsocial.dtos.LoginDTO;
import com.mycompany.redsocial.dtos.LoginResponseDTO;
import com.mycompany.redsocial.dtos.RegistroDTO;
import com.mycompany.redsocial.dtos.UsuarioDTO;
import com.mycompany.redsocial.models.Usuario;
import com.mycompany.redsocial.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Obtener todos los usuarios
    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    // Registro de usuario
    public Usuario registrarUsuario(RegistroDTO registroDTO) {
        // Verificar si el username ya está en uso
        if (usuarioRepository.findByUsername(registroDTO.getUsername()).isPresent()) {
            throw new RuntimeException("El nombre de usuario ya está en uso");
        }

        // Verificar si el email ya está en uso
        if (usuarioRepository.findByEmail(registroDTO.getEmail()).isPresent()) {
            throw new RuntimeException("El correo electrónico ya está en uso");
        }

        // Crear un nuevo usuario
        Usuario usuario = new Usuario();
        usuario.setUsername(registroDTO.getUsername());
        usuario.setPassword(passwordEncoder.encode(registroDTO.getPassword())); // Encriptar la contraseña
        usuario.setEmail(registroDTO.getEmail());

        // Guardar el usuario en la base de datos
        return usuarioRepository.save(usuario);
    }

    //Login
    public LoginResponseDTO loginUsuario(LoginDTO loginDTO) {
        Usuario usuario = usuarioRepository.findByUsername(loginDTO.getUsername())
                .orElseThrow(() -> new RuntimeException("Nombre de usuario o contraseña incorrectos"));

        if (!passwordEncoder.matches(loginDTO.getPassword(), usuario.getPassword())) {
            throw new RuntimeException("Nombre de usuario o contraseña incorrectos");
        }

        // Mapear la entidad Usuario a LoginResponseDTO
        LoginResponseDTO responseDTO = new LoginResponseDTO();
        responseDTO.setId(usuario.getId());
        responseDTO.setUsername(usuario.getUsername());
        responseDTO.setEmail(usuario.getEmail());
        responseDTO.setCellphone(usuario.getCellphone());
        responseDTO.setWebPage(usuario.getWebPage());
        responseDTO.setAddress(usuario.getAddress());
        responseDTO.setBirthDate(usuario.getBirthDate());
        responseDTO.setProfilePic(usuario.getProfilePic());
        responseDTO.setDescription(usuario.getDescription());

        return responseDTO;
    }

    // Actualizar usuario
    public Usuario actualizarUsuario(Integer id, UsuarioDTO usuarioDTO) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Actualizar campos solo si no son nulos
        if (usuarioDTO.getUsername() != null) {
            usuario.setUsername(usuarioDTO.getUsername());
        }
        if (usuarioDTO.getEmail() != null) {
            usuario.setEmail(usuarioDTO.getEmail());
        }
        if (usuarioDTO.getPassword() != null && !usuarioDTO.getPassword().isEmpty()) {
            usuario.setPassword(passwordEncoder.encode(usuarioDTO.getPassword()));
        }
        if (usuarioDTO.getCellphone() != null) {
            usuario.setCellphone(usuarioDTO.getCellphone());
        }
        if (usuarioDTO.getWebPage() != null) {
            usuario.setWebPage(usuarioDTO.getWebPage());
        }
        if (usuarioDTO.getAddress() != null) {
            usuario.setAddress(usuarioDTO.getAddress());
        }
        if (usuarioDTO.getBirthDate() != null) {
            usuario.setBirthDate(usuarioDTO.getBirthDate());
        }
        if (usuarioDTO.getProfilePic() != null) {
            usuario.setProfilePic(usuarioDTO.getProfilePic());
        }
        if (usuarioDTO.getDescription() != null) {
            usuario.setDescription(usuarioDTO.getDescription());
        }

        return usuarioRepository.save(usuario);
    }
    public Optional<Usuario> getUsuarioById(Integer id) {
        return usuarioRepository.findById(id);
    }
}