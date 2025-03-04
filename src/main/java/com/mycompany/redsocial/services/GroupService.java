package com.mycompany.redsocial.services;

import com.mycompany.redsocial.dtos.GroupDTO;
import com.mycompany.redsocial.models.Group;
import com.mycompany.redsocial.models.Moderator;
import com.mycompany.redsocial.models.Usuario;
import com.mycompany.redsocial.repositories.GroupRepository;
import com.mycompany.redsocial.repositories.ModeratorRepository;
import com.mycompany.redsocial.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GroupService {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private UsuarioRepository usuarioRepository; // Inyectar el repositorio

    @Autowired
    private ModeratorRepository moderatorRepository;

    // Crear un grupo
    public GroupDTO createGroup(GroupDTO groupDto) {
        // Verificar si el usuario creador existe
        Usuario usuarioCreator = usuarioRepository.findById(groupDto.getIdUsuarioCreator())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + groupDto.getIdUsuarioCreator()));

        // Crear el grupo
        Group group = new Group();
        group.setName(groupDto.getName());
        group.setDescription(groupDto.getDescription());
        group.setIdUsuarioCreator(groupDto.getIdUsuarioCreator()); // Asignar el ID del usuario creador
        Group savedGroup = groupRepository.save(group);

        // Crear el registro en Moderator
        Moderator moderator = new Moderator();
        moderator.setIdUsuario(usuarioCreator);
        moderator.setIdGroup(savedGroup);
        moderatorRepository.save(moderator);

        // Convertir la entidad guardada a DTO
        GroupDTO responseDto = new GroupDTO();
        responseDto.setId(savedGroup.getId());
        responseDto.setName(savedGroup.getName());
        responseDto.setDescription(savedGroup.getDescription());
        responseDto.setIdUsuarioCreator(savedGroup.getIdUsuarioCreator()); // Asignar el ID del usuario creador
        return responseDto;
    }

    // Obtener un grupo por ID
    public GroupDTO getGroupById(Integer id) {
        Optional<Group> groupOptional = groupRepository.findById(id);
        if (groupOptional.isPresent()) {
            Group group = groupOptional.get();
            GroupDTO groupDto = new GroupDTO();
            groupDto.setId(group.getId());
            groupDto.setName(group.getName());
            groupDto.setDescription(group.getDescription());
            return groupDto;
        } else {
            throw new RuntimeException("Grupo no encontrado con ID: " + id);
        }
    }

    // Obtener todos los grupos
    public List<Group> getAllGroups() {
        return groupRepository.findAll();
    }

    // Eliminar un grupo por ID
    public void deleteGroup(Integer id) {
        groupRepository.deleteById(id);
    }
}