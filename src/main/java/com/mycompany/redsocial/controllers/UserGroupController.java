package com.mycompany.redsocial.controllers;

import com.mycompany.redsocial.dtos.UserGroupDTO;
import com.mycompany.redsocial.models.UserGroup;
import com.mycompany.redsocial.services.UserGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user-groups")
public class UserGroupController {

    @Autowired
    private UserGroupService userGroupService;

    // Crear un UserGroup
    @PostMapping
    public ResponseEntity<UserGroup> createUserGroup(@RequestBody UserGroupDTO userGroupDTO) {
        UserGroup createdUserGroup = userGroupService.createUserGroup(userGroupDTO);
        return ResponseEntity.ok(createdUserGroup);
    }

    // Obtener un UserGroup por ID
    @GetMapping("/{id}")
    public ResponseEntity<UserGroup> getUserGroupById(@PathVariable Integer id) {
        Optional<UserGroup> userGroup = userGroupService.getUserGroupById(id);
        return userGroup.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Obtener todos los UserGroups
    @GetMapping
    public ResponseEntity<List<UserGroup>> getAllUserGroups() {
        List<UserGroup> userGroups = userGroupService.getAllUserGroups();
        return ResponseEntity.ok(userGroups);
    }



    // Eliminar un UserGroup
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserGroup(@PathVariable Integer id) {
        userGroupService.deleteUserGroup(id);
        return ResponseEntity.noContent().build();
    }
}