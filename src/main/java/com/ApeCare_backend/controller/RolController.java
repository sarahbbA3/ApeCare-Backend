package com.ApeCare_backend.controller;

import com.ApeCare_backend.dto.RolDTO;
import com.ApeCare_backend.service.RolService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
@RequiredArgsConstructor
@CrossOrigin("*")
public class RolController {
    private final RolService service;

    @PostMapping
    public RolDTO crear(@RequestBody RolDTO dto) {
        return service.crear(dto);
    }

    @GetMapping
    public List<RolDTO> listarActivos() {
        return service.listarActivos();
    }

    @PutMapping("/{id}")
    public ResponseEntity<RolDTO> editar(@PathVariable Long id, @RequestBody RolDTO dto) {
        return ResponseEntity.ok(service.editar(id, dto));
    }

    @PutMapping("/{id}/estado/{estadoId}")
    public void eliminar(@PathVariable Long id, @PathVariable Long estadoId) {
        service.eliminar(id, estadoId);
    }
}
