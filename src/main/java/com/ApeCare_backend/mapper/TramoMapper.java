package com.ApeCare_backend.mapper;

import com.ApeCare_backend.dto.TramoDTO;
import com.ApeCare_backend.entity.Estado;
import com.ApeCare_backend.entity.Estante;
import com.ApeCare_backend.entity.Tramo;

import java.time.LocalDate;

public class TramoMapper {

    public static TramoDTO toDTO(Tramo tramo) {
        LocalDate fc = tramo.getFechaCreacion().toLocalDate();
        LocalDate fa = tramo.getFechaActualizacion() != null
                ? tramo.getFechaActualizacion().toLocalDate()
                : fc;

        return new TramoDTO(
                tramo.getId(),
                tramo.getNombre(),
                tramo.getEstante().getId(),
                tramo.getEstante().getNombre(),
                fc,
                fa,
                tramo.getEstado().getId()
        );
    }

    public static Tramo toEntity(TramoDTO dto, Estante estante, Estado estado) {
        Tramo t = new Tramo();
        t.setId(dto.getId());
        t.setNombre(dto.getNombre());
        t.setEstante(estante);
        t.setEstado(estado);
        return t;
    }

}
