package com.ApeCare_backend.mapper;

import com.ApeCare_backend.dto.RolDTO;
import com.ApeCare_backend.entity.Estado;
import com.ApeCare_backend.entity.Rol;

public class RolMapper {
    public static RolDTO toDTO(Rol rol) {
        RolDTO dto = new RolDTO();
        dto.setId(rol.getId());
        dto.setNombre(rol.getNombre());
        dto.setDescripcion(rol.getDescripcion());
        dto.setFechaCreacion(rol.getFechaCreacion().toLocalDate());
        dto.setFechaActualizacion(
                rol.getFechaActualizacion() != null
                        ? rol.getFechaActualizacion().toLocalDate()
                        : rol.getFechaCreacion().toLocalDate()
        );
        return dto;
    }

    public static Rol toEntity(RolDTO dto, Estado estado) {
        Rol rol = new Rol();
        rol.setId(dto.getId());
        rol.setNombre(dto.getNombre());
        rol.setDescripcion(dto.getDescripcion());
        rol.setEstado(estado);
        return rol;
    }
}
