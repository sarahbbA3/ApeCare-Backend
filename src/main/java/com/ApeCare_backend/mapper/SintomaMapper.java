package com.ApeCare_backend.mapper;

import com.ApeCare_backend.dto.SintomaDTO;
import com.ApeCare_backend.entity.Estado;
import com.ApeCare_backend.entity.Sintoma;

import java.time.LocalDate;

public class SintomaMapper {

    public static SintomaDTO toDTO(Sintoma sintoma) {
        LocalDate fechaCreacion = sintoma.getFechaCreacion().toLocalDate();
        LocalDate fechaActualizacion = sintoma.getFechaActualizacion() != null
                ? sintoma.getFechaActualizacion().toLocalDate()
                : fechaCreacion;

        SintomaDTO dto = new SintomaDTO();
        dto.setId(sintoma.getId());
        dto.setNombre(sintoma.getNombre());
        dto.setDescripcion(sintoma.getDescripcion());
        dto.setFechaCreacion(fechaCreacion);
        dto.setFechaActualizacion(fechaActualizacion);
        return dto;
    }

    public static Sintoma toEntity(SintomaDTO dto, Estado estado) {
        Sintoma sintoma = new Sintoma();
        sintoma.setId(dto.getId());
        sintoma.setNombre(dto.getNombre());
        sintoma.setDescripcion(dto.getDescripcion());
        sintoma.setEstado(estado);
        return sintoma;
    }

}
