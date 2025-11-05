package com.ApeCare_backend.mapper;

import com.ApeCare_backend.dto.TipoPacienteDTO;
import com.ApeCare_backend.entity.Estado;
import com.ApeCare_backend.entity.TipoPaciente;

import java.time.LocalDate;

public class TipoPacienteMapper {

    public static TipoPacienteDTO toDTO(TipoPaciente tipo) {
        LocalDate fechaCreacion = tipo.getFechaCreacion().toLocalDate();
        LocalDate fechaActualizacion = tipo.getFechaActualizacion() != null
                ? tipo.getFechaActualizacion().toLocalDate()
                : fechaCreacion;

        TipoPacienteDTO dto = new TipoPacienteDTO();
        dto.setId(tipo.getId());
        dto.setNombre(tipo.getNombre());
        dto.setDescripcion(tipo.getDescripcion());
        dto.setFechaCreacion(fechaCreacion);
        dto.setFechaActualizacion(fechaActualizacion);
        return dto;
    }

    public static TipoPaciente toEntity(TipoPacienteDTO dto, Estado estado) {
        TipoPaciente tipo = new TipoPaciente();
        tipo.setId(dto.getId());
        tipo.setNombre(dto.getNombre());
        tipo.setDescripcion(dto.getDescripcion());
        tipo.setEstado(estado);
        return tipo;
    }

}
