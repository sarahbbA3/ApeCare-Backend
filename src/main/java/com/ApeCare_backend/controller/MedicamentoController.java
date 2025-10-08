package com.ApeCare_backend.controller;

import com.ApeCare_backend.dto.MedicamentoDTO;
import com.ApeCare_backend.service.MedicamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medicamentos")
@RequiredArgsConstructor
@CrossOrigin("*")
public class MedicamentoController {

    private final MedicamentoService service;

    @PostMapping
    public MedicamentoDTO crear(@RequestBody MedicamentoDTO dto) {
        return service.crear(dto);
    }

    @PutMapping("/{id}")
    public MedicamentoDTO editar(@PathVariable Long id, @RequestBody MedicamentoDTO dto) {
        return service.editar(id, dto);
    }

    @GetMapping("/{id}")
    public MedicamentoDTO buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @GetMapping
    public List<MedicamentoDTO> listarActivos() {
        return service.listarActivos();
    }

    @PutMapping("/{id}/estado/{estadoId}")
    public void eliminar(@PathVariable Long id, @PathVariable Long estadoId) {
        service.eliminar(id, estadoId);
    }


}
