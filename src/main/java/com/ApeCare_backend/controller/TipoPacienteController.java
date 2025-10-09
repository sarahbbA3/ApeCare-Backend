package com.ApeCare_backend.controller;

import com.ApeCare_backend.dto.TipoPacienteDTO;
import com.ApeCare_backend.service.TipoPacienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tipos-paciente")
@RequiredArgsConstructor
@CrossOrigin("*")
public class TipoPacienteController {

    private final TipoPacienteService service;

    @PostMapping
    public ResponseEntity<TipoPacienteDTO> crear(@RequestBody TipoPacienteDTO dto) {
        return new ResponseEntity<>(service.crear(dto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipoPacienteDTO> editar(@PathVariable Long id, @RequestBody TipoPacienteDTO dto) {
        dto.setId(id);
        return ResponseEntity.ok(service.editar(dto));
    }

    @GetMapping
    public ResponseEntity<List<TipoPacienteDTO>> listarActivos() {
        return ResponseEntity.ok(service.listarActivos());
    }

    @PutMapping("/{id}/estado/{estadoId}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id, @PathVariable Long estadoId) {
        service.eliminar(id, estadoId);
        return ResponseEntity.noContent().build();
    }


}
