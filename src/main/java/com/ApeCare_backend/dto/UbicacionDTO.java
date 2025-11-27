package com.ApeCare_backend.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UbicacionDTO {
    private Long id;
    private String nombre;
    private Long celdaId;
    private String celdaNombre;
    private String tramoNombre;
    private String estanteNombre;
    private Long tipoFarmacoId;
    private String tipoFarmacoNombre;
    private Long estadoId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate fechaCreacion;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate fechaActualizacion;
}
