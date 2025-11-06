package com.ApeCare_backend.service;

import com.ApeCare_backend.dto.UsuarioDTO;

import java.util.List;

public interface UsuarioService {
    UsuarioDTO crear(UsuarioDTO dto);
    List<UsuarioDTO> listarActivos();
    UsuarioDTO editar(Long id, UsuarioDTO dto);
    void eliminar(Long id, Long estadoId);
}
