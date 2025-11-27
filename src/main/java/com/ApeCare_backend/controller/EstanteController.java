package com.ApeCare_backend.controller;

import com.ApeCare_backend.dto.EstanteDTO;
import com.ApeCare_backend.service.EstanteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/estantes")
@RequiredArgsConstructor
@CrossOrigin("*")
public class EstanteController {

    private final EstanteService estanteService;

    @PostMapping
    public ResponseEntity<EstanteDTO> crear(@RequestBody EstanteDTO dto) {
        return new ResponseEntity<>(estanteService.crear(dto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<EstanteDTO>> listarActivos() {
        return ResponseEntity.ok(estanteService.listarActivos());
    }

    @PutMapping("/{id}")
    public ResponseEntity<EstanteDTO> editar(@PathVariable Long id, @RequestBody EstanteDTO dto) {
        dto.setId(id);
        return ResponseEntity.ok(estanteService.editar(dto));
    }

    @PutMapping("/{id}/estado/{estadoId}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id, @PathVariable Long estadoId) {
        estanteService.eliminar(id, estadoId);
        return ResponseEntity.noContent().build();
    }

}
