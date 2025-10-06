package com.ApeCare_backend.service;

import com.ApeCare_backend.dto.UbicacionDTO;

import java.util.List;

public interface UbicacionService {

    UbicacionDTO crear(UbicacionDTO dto);
    List<UbicacionDTO> listarActivos();
    void eliminar(Long id, Long estadoId);

}
