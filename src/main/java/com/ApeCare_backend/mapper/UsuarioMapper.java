package com.ApeCare_backend.mapper;

import com.ApeCare_backend.dto.UsuarioDTO;
import com.ApeCare_backend.entity.Estado;
import com.ApeCare_backend.entity.Rol;
import com.ApeCare_backend.entity.Usuario;

public class UsuarioMapper {
    public static UsuarioDTO toDTO(Usuario usuario) {
        UsuarioDTO dto = new UsuarioDTO();
        dto.setId(usuario.getId());
        dto.setCorreo(usuario.getCorreo());
        dto.setNombre(usuario.getNombre());
        dto.setContrasena(usuario.getContrasena());
        dto.setEstadoId(usuario.getEstado().getId());
        dto.setRolId(usuario.getRol().getId());
        dto.setFechaCreacion(usuario.getFechaCreacion().toLocalDate());
        dto.setFechaActualizacion(
                usuario.getFechaActualizacion() != null
                        ? usuario.getFechaActualizacion().toLocalDate()
                        : usuario.getFechaCreacion().toLocalDate()
        );
        return dto;
    }

    public static Usuario toEntity(UsuarioDTO dto, Estado estado, Rol rol) {
        Usuario usuario = new Usuario();
        usuario.setId(dto.getId());
        usuario.setCorreo(dto.getCorreo());
        usuario.setNombre(dto.getNombre());
        usuario.setContrasena(dto.getContrasena());
        usuario.setEstado(estado);
        usuario.setRol(rol);
        return usuario;
    }
}
