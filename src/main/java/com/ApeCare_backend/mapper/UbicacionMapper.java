package com.ApeCare_backend.mapper;

import com.ApeCare_backend.dto.UbicacionDTO;
import com.ApeCare_backend.entity.Estado;
import com.ApeCare_backend.entity.Ubicacion;

public class UbicacionMapper {

    public static UbicacionDTO toDTO(Ubicacion ubicacion) {
        return new UbicacionDTO(
                ubicacion.getId(),
                ubicacion.getEstante(),
                ubicacion.getTramo(),
                ubicacion.getCelda(),
                ubicacion.getFechaCreacion().toLocalDate(),
                ubicacion.getEstado().getId()
        );
    }

    public static Ubicacion toEntity(UbicacionDTO dto, Estado estado) {
        Ubicacion ubicacion = new Ubicacion();
        ubicacion.setId(dto.getId());
        ubicacion.setEstante(dto.getEstante());
        ubicacion.setTramo(dto.getTramo());
        ubicacion.setCelda(dto.getCelda());
        ubicacion.setEstado(estado);
        return ubicacion;
    }
}
