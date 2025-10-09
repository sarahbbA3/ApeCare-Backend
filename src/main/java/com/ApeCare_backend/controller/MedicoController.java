package com.ApeCare_backend.controller;

import com.ApeCare_backend.dto.MedicoDTO;
import com.ApeCare_backend.service.MedicoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medicos")
@RequiredArgsConstructor
@CrossOrigin("*")
public class MedicoController {

    private final MedicoService service;

    @PostMapping
    public MedicoDTO crear(@RequestBody MedicoDTO dto) {
        return service.crear(dto);
    }

    @GetMapping
    public List<MedicoDTO> listarActivos() {
        return service.listarActivos();
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicoDTO> editar(@PathVariable Long id, @RequestBody MedicoDTO dto) {
        return ResponseEntity.ok(service.editar(id, dto));
    }

    @PutMapping("/{id}/estado/{estadoId}")
    public void eliminar(@PathVariable Long id, @PathVariable Long estadoId) {
        service.eliminar(id, estadoId);
    }

}
