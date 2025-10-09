package com.ApeCare_backend.controller;

import com.ApeCare_backend.dto.PacienteDTO;
import com.ApeCare_backend.service.PacienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pacientes")
@RequiredArgsConstructor
@CrossOrigin("*")
public class PacienteController {

    private final PacienteService service;

    @PostMapping
    public PacienteDTO crear(@RequestBody PacienteDTO dto) {
        return service.crear(dto);
    }

    @GetMapping
    public List<PacienteDTO> listarActivos() {
        return service.listarActivos();
    }

    @PutMapping("/{id}")
    public ResponseEntity<PacienteDTO> editar(@PathVariable Long id, @RequestBody PacienteDTO dto) {
        return ResponseEntity.ok(service.editar(id, dto));
    }

    @PutMapping("/{id}/estado/{estadoId}")
    public void eliminar(@PathVariable Long id, @PathVariable Long estadoId) {
        service.eliminar(id, estadoId);
    }

}
