package com.mycompany.redsocial.controllers;

import com.mycompany.redsocial.dtos.ModeratorDTO;
import com.mycompany.redsocial.models.Moderator;
import com.mycompany.redsocial.services.ModeratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/moderators")
public class ModeratorController {

    @Autowired
    private ModeratorService moderatorService;

    // Crear un moderador
    @PostMapping
    public ResponseEntity<ModeratorDTO> createModerator(@RequestBody ModeratorDTO moderatorDto) {
        ModeratorDTO createdModerator = moderatorService.createModerator(moderatorDto);
        return ResponseEntity.ok(createdModerator);
    }

    // Obtener un moderador por ID
    @GetMapping("/{id}")
    public ResponseEntity<ModeratorDTO> getModeratorById(@PathVariable Integer id) {
        ModeratorDTO moderatorDto = moderatorService.getModeratorById(id);
        return ResponseEntity.ok(moderatorDto);
    }

    // Obtener todos los moderadores
    @GetMapping
    public ResponseEntity<List<Moderator>> getAllModerators() {
        List<Moderator> moderators = moderatorService.getAllModerators();
        return ResponseEntity.ok(moderators);
    }

    // Actualizar un moderador
    @PutMapping("/{id}")
    public ResponseEntity<ModeratorDTO> updateModerator(@PathVariable Integer id, @RequestBody ModeratorDTO moderatorDto) {
        ModeratorDTO updatedModerator = moderatorService.updateModerator(id, moderatorDto);
        return ResponseEntity.ok(updatedModerator);
    }

    // Eliminar un moderador por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteModerator(@PathVariable Integer id) {
        moderatorService.deleteModerator(id);
        return ResponseEntity.noContent().build();
    }
}