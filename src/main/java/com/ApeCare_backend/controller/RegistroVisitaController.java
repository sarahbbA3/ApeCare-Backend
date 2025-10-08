package com.ApeCare_backend.controller;

import com.ApeCare_backend.dto.RegistroVisitaDTO;
import com.ApeCare_backend.service.RegistroVisitaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/visitas")
@RequiredArgsConstructor
@CrossOrigin("*")
public class RegistroVisitaController {

    private final RegistroVisitaService service;

    @PostMapping
    public RegistroVisitaDTO crear(@RequestBody RegistroVisitaDTO dto) {
        return service.crear(dto);
    }

    @GetMapping
    public List<RegistroVisitaDTO> listarActivos() {
        return service.listarActivos();
    }

    @PutMapping("/{id}/estado/{estadoId}")
    public void eliminar(@PathVariable Long id, @PathVariable Long estadoId) {
        service.eliminar(id, estadoId);
    }

}
