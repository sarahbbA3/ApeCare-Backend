package com.ApeCare_backend.mapper;

import com.ApeCare_backend.dto.EstanteDTO;
import com.ApeCare_backend.entity.Estado;
import com.ApeCare_backend.entity.Estante;

import java.time.LocalDate;

public class EstanteMapper {

    public static EstanteDTO toDTO(Estante estante) {
        LocalDate fc = estante.getFechaCreacion().toLocalDate();
        LocalDate fa = estante.getFechaActualizacion() != null
                ? estante.getFechaActualizacion().toLocalDate()
                : fc;

        return new EstanteDTO(
                estante.getId(),
                estante.getNombre(),
                estante.getEstado().getId(),
                fc,
                fa
        );
    }

    public static Estante toEntity(EstanteDTO dto, Estado estado) {
        Estante e = new Estante();
        e.setId(dto.getId());
        e.setNombre(dto.getNombre());
        e.setEstado(estado);
        return e;
    }

}
