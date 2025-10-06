package com.ApeCare_backend.mapper;

import com.ApeCare_backend.dto.EstadoDTO;
import com.ApeCare_backend.entity.Estado;

public class EstadoMapper {
    public static EstadoDTO toDTO(Estado estado) {
        if (estado == null) return null;
        return new EstadoDTO(
                estado.getId(),
                estado.getNombre(),
                estado.getDescripcion()
        );
    }

    public static Estado toEntity(EstadoDTO dto) {
        if (dto == null) return null;
        return new Estado(
                dto.getId(),
                dto.getNombre(),
                dto.getDescripcion()
        );
    }

}
