package com.ApeCare_backend.mapper;

import com.ApeCare_backend.dto.RegistroVisitaSintomaDTO;
import com.ApeCare_backend.entity.Estado;
import com.ApeCare_backend.entity.RegistroVisita;
import com.ApeCare_backend.entity.RegistroVisitaSintoma;
import com.ApeCare_backend.entity.Sintoma;

public class RegistroVisitaSintomaMapper {

    public static RegistroVisitaSintomaDTO toDTO(RegistroVisitaSintoma entity) {
        RegistroVisitaSintomaDTO dto = new RegistroVisitaSintomaDTO();
        dto.setId(entity.getId());
        dto.setRegistroVisitaId(entity.getRegistroVisita().getId());
        dto.setSintomaId(entity.getSintoma().getId());
        dto.setFechaCreacion(entity.getFechaCreacion().toLocalDate());
        dto.setFechaActualizacion(entity.getFechaActualizacion() != null
                ? entity.getFechaActualizacion().toLocalDate()
                : null);
        return dto;
    }

    public static RegistroVisitaSintoma toEntity(RegistroVisitaSintomaDTO dto, RegistroVisita visita, Sintoma sintoma, Estado estado) {
        RegistroVisitaSintoma entity = new RegistroVisitaSintoma();
        entity.setId(dto.getId());
        entity.setRegistroVisita(visita);
        entity.setSintoma(sintoma);
        entity.setEstado(estado);
        return entity;
    }

}
