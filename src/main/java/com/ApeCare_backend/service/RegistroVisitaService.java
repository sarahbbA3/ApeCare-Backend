package com.ApeCare_backend.service;

import com.ApeCare_backend.dto.RegistroVisitaDTO;

import java.util.List;

public interface RegistroVisitaService {

    RegistroVisitaDTO crear(RegistroVisitaDTO dto);
    List<RegistroVisitaDTO> listarActivos();
    RegistroVisitaDTO editar(Long id, RegistroVisitaDTO dto);
    void eliminar(Long id, Long estadoId);

}
