package com.ApeCare_backend.service;

import com.ApeCare_backend.dto.MarcaDTO;

import java.util.List;

public interface MarcaService {
    MarcaDTO crear(MarcaDTO dto);
    List<MarcaDTO> listarActivos();
    void eliminar(Long id, Long estadoId);
}
