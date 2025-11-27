package com.ApeCare_backend.service;

import com.ApeCare_backend.dto.EstanteDTO;

import java.util.List;

public interface EstanteService {

    EstanteDTO crear(EstanteDTO dto);
    List<EstanteDTO> listarActivos();
    EstanteDTO editar(EstanteDTO dto);
    void eliminar(Long id, Long estadoId);

}
