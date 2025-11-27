package com.ApeCare_backend.service;

import com.ApeCare_backend.dto.CeldaDTO;

import java.util.List;

public interface CeldaService {
    CeldaDTO crear(CeldaDTO dto);
    List<CeldaDTO> listarActivos();
    CeldaDTO editar(CeldaDTO dto);
    void eliminar(Long id, Long estadoId);
}
