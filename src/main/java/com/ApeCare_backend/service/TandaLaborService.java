package com.ApeCare_backend.service;

import com.ApeCare_backend.dto.TandaLaborDTO;

import java.util.List;

public interface TandaLaborService {

    TandaLaborDTO crear(TandaLaborDTO dto);
    List<TandaLaborDTO> listarActivos();
    void eliminar(Long id, Long estadoId);

}
