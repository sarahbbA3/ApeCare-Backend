package com.ApeCare_backend.controller;

import com.ApeCare_backend.dto.TramoDTO;
import com.ApeCare_backend.service.TramoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tramos")
@RequiredArgsConstructor
@CrossOrigin("*")
public class TramoController {

    private final TramoService tramoService;

    @PostMapping
    public ResponseEntity<TramoDTO> crear(@RequestBody TramoDTO dto) {
        return new ResponseEntity<>(tramoService.crear(dto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TramoDTO>> listarActivos() {
        return ResponseEntity.ok(tramoService.listarActivos());
    }

    @PutMapping("/{id}")
    public ResponseEntity<TramoDTO> editar(@PathVariable Long id, @RequestBody TramoDTO dto) {
        dto.setId(id);
        return ResponseEntity.ok(tramoService.editar(dto));
    }

    @PutMapping("/{id}/estado/{estadoId}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id, @PathVariable Long estadoId) {
        tramoService.eliminar(id, estadoId);
        return ResponseEntity.noContent().build();
    }

}
