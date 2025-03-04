package com.mycompany.redsocial.services;

import com.mycompany.redsocial.dtos.UserGroupDTO;
import com.mycompany.redsocial.models.UserGroup;
import com.mycompany.redsocial.models.Usuario;
import com.mycompany.redsocial.models.Group;
import com.mycompany.redsocial.repositories.UserGroupRepository;
import com.mycompany.redsocial.repositories.UsuarioRepository;
import com.mycompany.redsocial.repositories.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class UserGroupService {

    @Autowired
    private UserGroupRepository userGroupRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private GroupRepository groupRepository;

    // Crear un UserGroup
    public UserGroup createUserGroup(UserGroupDTO userGroupDTO) {
        UserGroup userGroup = new UserGroup();

        // Buscar y asignar Usuario
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(userGroupDTO.getIdUsuario());
        usuarioOptional.ifPresent(userGroup::setIdUsuario);

        // Buscar y asignar Group
        Optional<Group> groupOptional = groupRepository.findById(userGroupDTO.getIdGroup());
        groupOptional.ifPresent(userGroup::setIdGroup);

        // Asignar el estado
        userGroup.setState(userGroupDTO.getState());

        // Asignar la fecha actual manualmente
        userGroup.setJoinDate(LocalDate.now()); // Esto asegura que joinDate no sea null

        return userGroupRepository.save(userGroup);
    }

    // Obtener un UserGroup por ID
    public Optional<UserGroup> getUserGroupById(Integer id) {
        return userGroupRepository.findById(id);
    }

    // Obtener todos los UserGroups
    public List<UserGroup> getAllUserGroups() {
        return userGroupRepository.findAll();
    }


    // Eliminar un UserGroup
    public void deleteUserGroup(Integer id) {
        userGroupRepository.deleteById(id);
    }
}