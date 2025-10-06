package com.ApeCare_backend.mapper;

import com.ApeCare_backend.dto.MarcaDTO;
import com.ApeCare_backend.entity.Estado;
import com.ApeCare_backend.entity.Marca;

public class MarcaMapper {

    public static MarcaDTO toDTO(Marca marca) {
        return new MarcaDTO(
                marca.getId(),
                marca.getNombre(),
                marca.getDescripcion(),
                marca.getFechaCreacion().toLocalDate(),
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
