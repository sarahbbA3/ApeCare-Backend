package com.ApeCare_backend.mapper;

import com.ApeCare_backend.dto.EspecialidadDTO;
import com.ApeCare_backend.entity.Especialidad;
import com.ApeCare_backend.entity.Estado;

public class EspecialidadMapper {

    public static EspecialidadDTO toDTO(Especialidad especialidad) {
        EspecialidadDTO dto = new EspecialidadDTO();
        dto.setId(especialidad.getId());
        dto.setNombre(especialidad.getNombre());
        dto.setDescripcion(especialidad.getDescripcion());
        dto.setFechaCreacion(especialidad.getFechaCreacion().toLocalDate());
        return dto;
    }

    public static Especialidad toEntity(EspecialidadDTO dto, Estado estado) {
        Especialidad especialidad = new Especialidad();
        especialidad.setId(dto.getId());
        especialidad.setNombre(dto.getNombre());
        especialidad.setDescripcion(dto.getDescripcion());
        especialidad.setEstado(estado);
        return especialidad;
    }

}
