package com.ApeCare_backend.mapper;

import com.ApeCare_backend.dto.TipoFarmacoDTO;
import com.ApeCare_backend.entity.Estado;
import com.ApeCare_backend.entity.TipoFarmaco;

import java.time.LocalDate;

public class TipoFarmacoMapper {

    public static TipoFarmacoDTO toDTO(TipoFarmaco tipo) {
        LocalDate fechaCreacion = tipo.getFechaCreacion().toLocalDate();
        LocalDate fechaActualizacion = tipo.getFechaActualizacion() != null
                ? tipo.getFechaActualizacion().toLocalDate()
                : fechaCreacion;

        return new TipoFarmacoDTO(
                tipo.getId(),
                tipo.getNombre(),
                tipo.getDescripcion(),
                fechaCreacion,
                fechaActualizacion,
                tipo.getEstado().getId()
        );
    }

    public static TipoFarmaco toEntity(TipoFarmacoDTO dto, Estado estado) {
        TipoFarmaco tipo = new TipoFarmaco();
        tipo.setId(dto.getId());
        tipo.setNombre(dto.getNombre());
        tipo.setDescripcion(dto.getDescripcion());
        tipo.setEstado(estado);
        return tipo;
    }

}
