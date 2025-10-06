package com.ApeCare_backend.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;

@Data

public class MedicamentoSuministradoDTO {

    private Long id;
    private Long medicamentoId;
    private Long visitaId;
    private Integer cantidadSuministrada;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate fechaCreacion;

}
