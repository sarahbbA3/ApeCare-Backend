package com.ApeCare_backend.mapper;

import com.ApeCare_backend.dto.UbicacionDTO;
import com.ApeCare_backend.entity.Celda;
import com.ApeCare_backend.entity.Estado;
import com.ApeCare_backend.entity.Ubicacion;

import java.time.LocalDate;

public class UbicacionMapper {

    public static UbicacionDTO toDTO(Ubicacion ubicacion) {
        LocalDate fc = ubicacion.getFechaCreacion().toLocalDate();
        LocalDate fa = ubicacion.getFechaActualizacion() != null
                ? ubicacion.getFechaActualizacion().toLocalDate()
                : fc;

        return new UbicacionDTO(
                ubicacion.getId(),
                ubicacion.getCelda().getId(),
                ubicacion.getEstado().getId(),
                fc,
                fa
        );
    }

    public static Ubicacion toEntity(UbicacionDTO dto, Celda celda, Estado estado) {
        Ubicacion u = new Ubicacion();
        u.setId(dto.getId());
        u.setCelda(celda);
        u.setEstado(estado);
        return u;
    }

}
