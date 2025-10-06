package com.ApeCare_backend.controller;

import com.ApeCare_backend.dto.TipoFarmacoDTO;
import com.ApeCare_backend.service.TipoFarmacoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tipos-farmaco")
@RequiredArgsConstructor
@CrossOrigin("*")
public class TipoFarmacoController {

    private final TipoFarmacoService tipoFarmacoService;

    @PostMapping
    public ResponseEntity<TipoFarmacoDTO> crear(@RequestBody TipoFarmacoDTO dto) {
        return new ResponseEntity<>(tipoFarmacoService.crear(dto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TipoFarmacoDTO>> listarActivos() {
        return ResponseEntity.ok(tipoFarmacoService.listarActivos());
    }

    @PutMapping("/{id}/estado/{estadoId}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id, @PathVariable Long estadoId) {
        tipoFarmacoService.eliminar(id, estadoId);
        return ResponseEntity.noContent().build();
    }

}
