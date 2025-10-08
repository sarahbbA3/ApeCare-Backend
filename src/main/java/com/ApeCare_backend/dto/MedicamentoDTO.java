package com.ApeCare_backend.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Data
@Service
@RequiredArgsConstructor
public class MedicamentoDTO {

    private Long id;
    private String descripcion;
    private String dosis;
    private Integer cantidadDisponible;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate fechaVencimiento;
    private Long tipoFarmacoId;
    private Long ubicacionId;
    private Long marcaId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate fechaCreacion;

}
