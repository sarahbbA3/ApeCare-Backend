package com.ApeCare_backend.service;

import com.ApeCare_backend.dto.EspecialidadDTO;

import java.util.List;

public interface EspecialidadService {

    EspecialidadDTO crear(EspecialidadDTO dto);
    List<EspecialidadDTO> listarActivos();
    EspecialidadDTO editar(Long id, EspecialidadDTO dto);
    void eliminar(Long id, Long estadoId);

}
