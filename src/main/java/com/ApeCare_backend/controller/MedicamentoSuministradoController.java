package com.ApeCare_backend.controller;

import com.ApeCare_backend.dto.MedicamentoSuministradoDTO;
import com.ApeCare_backend.service.MedicamentoSuministradoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medicamentos-suministrados")
@RequiredArgsConstructor
@CrossOrigin("*")
public class MedicamentoSuministradoController {

    private final MedicamentoSuministradoService service;

    @PostMapping
    public MedicamentoSuministradoDTO crear(@RequestBody MedicamentoSuministradoDTO dto) {
        return service.crear(dto);
    }

    @GetMapping
    public List<MedicamentoSuministradoDTO> listarActivos() {
        return service.listarActivos();
    }

    @PutMapping("/{id}/estado/{estadoId}")
    public void eliminar(@PathVariable Long id, @PathVariable Long estadoId) {
        service.eliminar(id, estadoId);
    }

    @PutMapping("/{id}")
    public MedicamentoSuministradoDTO editar(@PathVariable Long id, @RequestBody MedicamentoSuministradoDTO dto) {
        return service.editar(id, dto);
    }
}
