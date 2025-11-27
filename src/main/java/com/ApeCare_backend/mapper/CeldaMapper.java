package com.ApeCare_backend.mapper;

import com.ApeCare_backend.dto.CeldaDTO;
import com.ApeCare_backend.entity.Celda;
import com.ApeCare_backend.entity.Estado;
import com.ApeCare_backend.entity.Estante;
import com.ApeCare_backend.entity.Tramo;

import java.time.LocalDate;

public class CeldaMapper {
    public static CeldaDTO toDTO(Celda celda) {
        LocalDate fc = celda.getFechaCreacion().toLocalDate();
        LocalDate fa = celda.getFechaActualizacion() != null
                ? celda.getFechaActualizacion().toLocalDate()
                : fc;

        Tramo tramo = celda.getTramo();
        Estante estante = tramo.getEstante();

        return new CeldaDTO(
                celda.getId(),
                celda.getNombre(),
                tramo.getId(),
                tramo.getNombre(),        
                estante.getNombre(),
                fc,
                fa,
                celda.getEstado().getId()
        );
    }

    public static Celda toEntity(CeldaDTO dto, Tramo tramo, Estado estado) {
        Celda c = new Celda();
        c.setId(dto.getId());
        c.setNombre(dto.getNombre());
        c.setTramo(tramo);
        c.setEstado(estado);
        return c;
    }
}
