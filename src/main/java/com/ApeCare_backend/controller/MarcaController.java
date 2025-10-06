package com.ApeCare_backend.controller;

import com.ApeCare_backend.dto.MarcaDTO;
import com.ApeCare_backend.service.MarcaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/marcas")
@RequiredArgsConstructor
@CrossOrigin("*")
public class MarcaController {

    private final MarcaService marcaService;

    @PostMapping
    public ResponseEntity<MarcaDTO> crear(@RequestBody MarcaDTO dto) {
        return new ResponseEntity<>(marcaService.crear(dto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<MarcaDTO>> listarActivos() {
        return ResponseEntity.ok(marcaService.listarActivos());
    }

    @PutMapping("/{id}/estado/{estadoId}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id, @PathVariable Long estadoId) {
        marcaService.eliminar(id, estadoId);
        return ResponseEntity.noContent().build();
    }

}
