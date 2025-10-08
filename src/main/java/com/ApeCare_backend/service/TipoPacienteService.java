package com.ApeCare_backend.service;

import com.ApeCare_backend.dto.TipoPacienteDTO;

import java.util.List;

public interface TipoPacienteService {

    TipoPacienteDTO crear(TipoPacienteDTO dto);
    List<TipoPacienteDTO> listarActivos();
    void eliminar(Long id, Long estadoId);

}
