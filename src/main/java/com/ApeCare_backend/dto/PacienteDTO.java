package com.ApeCare_backend.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.time.LocalDate;

@Data
public class PacienteDTO {

    private Long id;
    private String nombre;
    private String cedula;
    private String numeroCarnet;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate fechaRegistro;
    private Long tipoPacienteId;

}
