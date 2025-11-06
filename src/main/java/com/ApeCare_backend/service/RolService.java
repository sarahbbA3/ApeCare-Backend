package com.ApeCare_backend.service;

import com.ApeCare_backend.dto.RolDTO;

import java.util.List;

public interface RolService {
    RolDTO crear(RolDTO dto);
    List<RolDTO> listarActivos();
    RolDTO editar(Long id, RolDTO dto);
    void eliminar(Long id, Long estadoId);
}
