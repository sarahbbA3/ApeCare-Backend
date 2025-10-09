package com.ApeCare_backend.controller;

import com.ApeCare_backend.dto.SintomaDTO;
import com.ApeCare_backend.service.SintomaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sintomas")
@RequiredArgsConstructor
@CrossOrigin("*")
public class SintomaController {

    private final SintomaService service;

    @PostMapping
    public SintomaDTO crear(@RequestBody SintomaDTO dto) {
        return service.crear(dto);
    }

    @GetMapping
    public ResponseEntity<List<SintomaDTO>> listarActivos() {
        return new ResponseEntity<>(service.listarActivos(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SintomaDTO> editar(@PathVariable Long id, @RequestBody SintomaDTO dto) {
        dto.setId(id);
        return ResponseEntity.ok(service.editar(dto));
    }

    @PutMapping("/{id}/estado/{estadoId}")
    public void eliminar(@PathVariable Long id, @PathVariable Long estadoId) {
        service.eliminar(id, estadoId);
    }

}
