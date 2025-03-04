package com.mycompany.redsocial.services;

import com.mycompany.redsocial.dtos.ModeratorDTO;
import com.mycompany.redsocial.models.Moderator;
import com.mycompany.redsocial.models.Usuario;
import com.mycompany.redsocial.models.Group;
import com.mycompany.redsocial.repositories.ModeratorRepository;
import com.mycompany.redsocial.repositories.UsuarioRepository;
import com.mycompany.redsocial.repositories.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ModeratorService {

    @Autowired
    private ModeratorRepository moderatorRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private GroupRepository groupRepository;

    // Crear un moderador
    public ModeratorDTO createModerator(ModeratorDTO moderatorDto) {
        // Verificar si el usuario y el grupo existen
        Usuario usuario = usuarioRepository.findById(moderatorDto.getIdUsuario())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + moderatorDto.getIdUsuario()));

        Group group = groupRepository.findById(moderatorDto.getIdGroup())
                .orElseThrow(() -> new RuntimeException("Grupo no encontrado con ID: " + moderatorDto.getIdGroup()));

        // Crear la entidad Moderator
        Moderator moderator = new Moderator();
        moderator.setIdUsuario(usuario);
        moderator.setIdGroup(group);

        // Guardar en la base de datos
        Moderator savedModerator = moderatorRepository.save(moderator);

        // Convertir la entidad guardada a DTO
        ModeratorDTO responseDto = new ModeratorDTO();
        responseDto.setId(savedModerator.getId());
        responseDto.setIdUsuario(savedModerator.getIdUsuario().getId());
        responseDto.setIdGroup(savedModerator.getIdGroup().getId());
        return responseDto;
    }

    // Obtener un moderador por ID
    public ModeratorDTO getModeratorById(Integer id) {
        Optional<Moderator> moderatorOptional = moderatorRepository.findById(id);
        if (moderatorOptional.isPresent()) {
            Moderator moderator = moderatorOptional.get();
            ModeratorDTO moderatorDto = new ModeratorDTO();
            moderatorDto.setId(moderator.getId());
            moderatorDto.setIdUsuario(moderator.getIdUsuario().getId());
            moderatorDto.setIdGroup(moderator.getIdGroup().getId());
            return moderatorDto;
        } else {
            throw new RuntimeException("Moderador no encontrado con ID: " + id);
        }
    }

    // Obtener todos los moderadores
    public List<Moderator> getAllModerators() {
        return moderatorRepository.findAll();
    }

    // Actualizar un moderador
    public ModeratorDTO updateModerator(Integer id, ModeratorDTO moderatorDto) {
        Optional<Moderator> moderatorOptional = moderatorRepository.findById(id);
        if (moderatorOptional.isPresent()) {
            Moderator moderator = moderatorOptional.get();

            // Verificar si el usuario y el grupo existen
            Usuario usuario = usuarioRepository.findById(moderatorDto.getIdUsuario())
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + moderatorDto.getIdUsuario()));

            Group group = groupRepository.findById(moderatorDto.getIdGroup())
                    .orElseThrow(() -> new RuntimeException("Grupo no encontrado con ID: " + moderatorDto.getIdGroup()));

            // Actualizar los datos
            moderator.setIdUsuario(usuario);
            moderator.setIdGroup(group);

            // Guardar en la base de datos
            Moderator updatedModerator = moderatorRepository.save(moderator);

            // Convertir la entidad actualizada a DTO
            ModeratorDTO responseDto = new ModeratorDTO();
            responseDto.setId(updatedModerator.getId());
            responseDto.setIdUsuario(updatedModerator.getIdUsuario().getId());
            responseDto.setIdGroup(updatedModerator.getIdGroup().getId());
            return responseDto;
        } else {
            throw new RuntimeException("Moderador no encontrado con ID: " + id);
        }
    }

    // Eliminar un moderador por ID
    public void deleteModerator(Integer id) {
        moderatorRepository.deleteById(id);
    }
}