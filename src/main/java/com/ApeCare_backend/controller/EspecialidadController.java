package com.ApeCare_backend.controller;

import com.ApeCare_backend.dto.EspecialidadDTO;
import com.ApeCare_backend.service.EspecialidadService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/especialidades")
@RequiredArgsConstructor
@CrossOrigin("*")
public class EspecialidadController {

    private final EspecialidadService service;

    @PostMapping
    public EspecialidadDTO crear(@RequestBody EspecialidadDTO dto) {
        return service.crear(dto);
    }

    @GetMapping
    public List<EspecialidadDTO> listarActivos() {
        return service.listarActivos();
    }

    @PutMapping("/{id}/estado/{estadoId}")
    public void eliminar(@PathVariable Long id, @PathVariable Long estadoId) {
        service.eliminar(id, estadoId);
    }

}
