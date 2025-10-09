package com.ApeCare_backend.controller;

import com.ApeCare_backend.dto.TandaLaborDTO;
import com.ApeCare_backend.service.TandaLaborService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tandas")
@RequiredArgsConstructor
@CrossOrigin("*")
public class TandaLaborController {

    private final TandaLaborService service;

    @PostMapping
    public TandaLaborDTO crear(@RequestBody TandaLaborDTO dto) {
        return service.crear(dto);
    }

    @GetMapping
    public List<TandaLaborDTO> listarActivos() {
        return service.listarActivos();
    }

    @PutMapping("/{id}")
    public ResponseEntity<TandaLaborDTO> editar(@PathVariable Long id, @RequestBody TandaLaborDTO dto) {
        return ResponseEntity.ok(service.editar(id, dto));
    }

    @PutMapping("/{id}/estado/{estadoId}")
    public void eliminar(@PathVariable Long id, @PathVariable Long estadoId) {
        service.eliminar(id, estadoId);
    }


}
