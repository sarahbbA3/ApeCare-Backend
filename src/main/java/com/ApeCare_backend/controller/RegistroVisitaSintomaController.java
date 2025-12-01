package com.ApeCare_backend.controller;

import com.ApeCare_backend.dto.RegistroVisitaSintomaDTO;
import com.ApeCare_backend.service.RegistroVisitaSintomaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/visita-sintomas")
@RequiredArgsConstructor
@CrossOrigin("*")
public class RegistroVisitaSintomaController {

    private final RegistroVisitaSintomaService service;

    @PostMapping
    public RegistroVisitaSintomaDTO crear(@RequestBody RegistroVisitaSintomaDTO dto) {
        return service.crear(dto);
    }

    @GetMapping
    public List<RegistroVisitaSintomaDTO> listarActivos() {
        return service.listarActivos();
    }

    @PutMapping("/{id}/estado/{estadoId}")
    public void eliminar(@PathVariable Long id, @PathVariable Long estadoId) {
        service.eliminar(id, estadoId);
    }

    @PutMapping("/{id}")
    public RegistroVisitaSintomaDTO editar(@PathVariable Long id, @RequestBody RegistroVisitaSintomaDTO dto) {
        return service.editar(id, dto);
    }

}
