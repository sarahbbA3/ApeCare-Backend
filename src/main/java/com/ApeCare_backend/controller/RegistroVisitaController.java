package com.ApeCare_backend.controller;

import com.ApeCare_backend.dto.RegistroVisitaDTO;
import com.ApeCare_backend.service.RegistroVisitaService;
import com.ApeCare_backend.service.ReporteVisitaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/visitas")
@RequiredArgsConstructor
@CrossOrigin("*")
public class RegistroVisitaController {

    private final RegistroVisitaService service;
    private final ReporteVisitaService reporteVisitaService;

    @PostMapping
    public RegistroVisitaDTO crear(@RequestBody RegistroVisitaDTO dto) {
        return service.crear(dto);
    }

    @GetMapping
    public List<RegistroVisitaDTO> listarActivos() {
        return service.listarActivos();
    }

    @PutMapping("/{id}")
    public ResponseEntity<RegistroVisitaDTO> editar(@PathVariable Long id, @RequestBody RegistroVisitaDTO dto) {
        return ResponseEntity.ok(service.editar(id, dto));
    }

    @PutMapping("/{id}/estado/{estadoId}")
    public void eliminar(@PathVariable Long id, @PathVariable Long estadoId) {
        service.eliminar(id, estadoId);
    }

    @GetMapping("/{id}/reporte/pdf")
    public ResponseEntity<byte[]> descargarReporteVisitaPDF(@PathVariable Long id) {
        try {
            byte[] pdf = reporteVisitaService.generarReporteVisitaPDF(id);
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=reporte_visita_" + id + ".pdf")
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(pdf);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al generar el PDF", e);
        }
    }

    @GetMapping("/reporte/pdf")
    public ResponseEntity<byte[]> descargarReporteVisitasPDF(
            @RequestParam(required = false) Long medicoId,
            @RequestParam(required = false) Long pacienteId,
            @RequestParam(required = false) String fecha) {
        try {
            byte[] pdf = reporteVisitaService.generarReporteVisitasPDF(medicoId, pacienteId, fecha);
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=reporte_visitas.pdf")
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(pdf);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al generar el PDF", e);
        }
    }
}
