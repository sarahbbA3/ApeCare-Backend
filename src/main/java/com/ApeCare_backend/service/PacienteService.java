package com.ApeCare_backend.service;

import com.ApeCare_backend.dto.PacienteDTO;

import java.util.List;

public interface PacienteService {

    PacienteDTO crear(PacienteDTO dto);
    List<PacienteDTO> listarActivos();
    PacienteDTO editar(Long id, PacienteDTO dto);
    void eliminar(Long id, Long estadoId);

}
