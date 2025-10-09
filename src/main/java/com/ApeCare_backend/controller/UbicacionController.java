package com.ApeCare_backend.controller;

import com.ApeCare_backend.dto.UbicacionDTO;
import com.ApeCare_backend.service.UbicacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ubicaciones")
@RequiredArgsConstructor
@CrossOrigin("*")
public class UbicacionController {

    private final UbicacionService ubicacionService;

    @PostMapping
    public ResponseEntity<UbicacionDTO> crear(@RequestBody UbicacionDTO dto) {
        return new ResponseEntity<>(ubicacionService.crear(dto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<UbicacionDTO>> listarActivos() {
        return ResponseEntity.ok(ubicacionService.listarActivos());
    }

    @PutMapping("/{id}")
    public ResponseEntity<UbicacionDTO> editar(@PathVariable Long id, @RequestBody UbicacionDTO dto) {
        dto.setId(id);
        return ResponseEntity.ok(ubicacionService.editar(dto));
    }

    @PutMapping("/{id}/estado/{estadoId}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id, @PathVariable Long estadoId) {
        ubicacionService.eliminar(id, estadoId);
        return ResponseEntity.noContent().build();
    }

}
