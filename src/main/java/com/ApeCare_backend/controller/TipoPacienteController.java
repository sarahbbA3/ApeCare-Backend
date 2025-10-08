package com.ApeCare_backend.controller;

import com.ApeCare_backend.dto.TipoPacienteDTO;
import com.ApeCare_backend.service.TipoPacienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tipos-paciente")
@RequiredArgsConstructor
@CrossOrigin("*")
public class TipoPacienteController {

    private final TipoPacienteService service;

    @PostMapping
    public TipoPacienteDTO crear(@RequestBody TipoPacienteDTO dto) {
        return service.crear(dto);
    }

    @GetMapping
    public List<TipoPacienteDTO> listarActivos() {
        return service.listarActivos();
    }

    @PutMapping("/{id}/estado/{estadoId}")
    public void eliminar(@PathVariable Long id, @PathVariable Long estadoId) {
        service.eliminar(id, estadoId);
    }

}
