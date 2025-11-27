package com.ApeCare_backend.service;

import com.ApeCare_backend.dto.TramoDTO;

import java.util.List;

public interface TramoService {

    TramoDTO crear(TramoDTO dto);
    List<TramoDTO> listarActivos();
    TramoDTO editar(TramoDTO dto);
    void eliminar(Long id, Long estadoId);

}
