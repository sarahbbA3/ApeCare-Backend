package com.ApeCare_backend.controller;

import com.ApeCare_backend.dto.CeldaDTO;
import com.ApeCare_backend.service.CeldaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/celdas")
@RequiredArgsConstructor
@CrossOrigin("*")
public class CeldaController {
    private final CeldaService celdaService;

    @PostMapping
    public ResponseEntity<CeldaDTO> crear(@RequestBody CeldaDTO dto) {
        return new ResponseEntity<>(celdaService.crear(dto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CeldaDTO>> listarActivos() {
        return ResponseEntity.ok(celdaService.listarActivos());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CeldaDTO> editar(@PathVariable Long id, @RequestBody CeldaDTO dto) {
        dto.setId(id);
        return ResponseEntity.ok(celdaService.editar(dto));
    }

    @PutMapping("/{id}/estado/{estadoId}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id, @PathVariable Long estadoId) {
        celdaService.eliminar(id, estadoId);
        return ResponseEntity.noContent().build();
    }
}
