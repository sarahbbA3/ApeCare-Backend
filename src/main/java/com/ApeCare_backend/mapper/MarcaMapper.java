package com.ApeCare_backend.mapper;

import com.ApeCare_backend.dto.MarcaDTO;
import com.ApeCare_backend.entity.Estado;
import com.ApeCare_backend.entity.Marca;

import java.time.LocalDate;

public class MarcaMapper {

    public static MarcaDTO toDTO(Marca marca) {
        LocalDate fechaCreacion = marca.getFechaCreacion().toLocalDate();
        LocalDate fechaActualizacion = marca.getFechaActualizacion() != null
                ? marca.getFechaActualizacion().toLocalDate()
                : fechaCreacion;

        return new MarcaDTO(
                marca.getId(),
                marca.getNombre(),
                marca.getDescripcion(),
                fechaCreacion,
                fechaActualizacion,
                marca.getEstado().getId()
        );
    }

    public static Marca toEntity(MarcaDTO dto, Estado estado) {
        Marca marca = new Marca();
        marca.setId(dto.getId());
        marca.setNombre(dto.getNombre());
        marca.setDescripcion(dto.getDescripcion());
        marca.setEstado(estado);
        return marca;
    }

}
