package com.mycompany.redsocial.controllers;
import com.mycompany.redsocial.dtos.GroupDTO;
import com.mycompany.redsocial.models.Group;
import com.mycompany.redsocial.services.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/groups")
public class GroupController {

    @Autowired
    private GroupService groupService;

    // Crear un grupo
    @PostMapping
    public ResponseEntity<GroupDTO> createGroup(@RequestBody GroupDTO groupDto) {
        GroupDTO createdGroup = groupService.createGroup(groupDto);
        return ResponseEntity.ok(createdGroup);
    }

    // Obtener un grupo por ID
    @GetMapping("/{id}")
    public ResponseEntity<GroupDTO> getGroupById(@PathVariable Integer id) {
        GroupDTO groupDto = groupService.getGroupById(id);
        return ResponseEntity.ok(groupDto);
    }

    // Obtener todos los grupos
    @GetMapping
    public ResponseEntity<List<Group>> getAllGroups() {
        List<Group> groups = groupService.getAllGroups();
        return ResponseEntity.ok(groups);
    }

    // Eliminar un grupo por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGroup(@PathVariable Integer id) {
        groupService.deleteGroup(id);
        return ResponseEntity.noContent().build();
    }

    // Endpoint para obtener los grupos de un usuario
    @GetMapping("/{userId}/my-groups")
    public List<Group> getGroupsByUserId(@PathVariable Integer userId) {
        return groupService.getGroupsByUserId(userId);
    }
}