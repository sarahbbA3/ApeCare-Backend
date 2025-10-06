package com.ApeCare_backend.controller;

import com.ApeCare_backend.dto.EstadoDTO;
import com.ApeCare_backend.service.EstadoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/estados")
@RequiredArgsConstructor
@CrossOrigin("*")
public class EstadoController {

    private final EstadoService estadoService;

    @GetMapping
    public ResponseEntity<List<EstadoDTO>> listar() {
        return ResponseEntity.ok(estadoService.listarEstados());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstadoDTO> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(estadoService.obtenerPorId(id));
    }

    @GetMapping("/seleccionables")
    public ResponseEntity<List<EstadoDTO>> listarParaFormulario() {
        List<EstadoDTO> estados = estadoService.listarEstados();
        return ResponseEntity.ok(estados);
    }

    @PostMapping
    public ResponseEntity<EstadoDTO> crear(@RequestBody EstadoDTO dto) {
        EstadoDTO creado = estadoService.crearEstado(dto);
        return new ResponseEntity<>(creado, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EstadoDTO> actualizar(@PathVariable Long id, @RequestBody EstadoDTO dto) {
        return ResponseEntity.ok(estadoService.actualizarEstado(id, dto));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        estadoService.eliminarEstado(id);
        return ResponseEntity.noContent().build();
    }

}
