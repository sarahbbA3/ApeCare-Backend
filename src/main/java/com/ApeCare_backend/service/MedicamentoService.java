package com.ApeCare_backend.service;

import com.ApeCare_backend.dto.MedicamentoDTO;

import java.util.List;

public interface MedicamentoService {

    MedicamentoDTO crear(MedicamentoDTO dto);
    MedicamentoDTO editar(Long id, MedicamentoDTO dto);
    MedicamentoDTO buscarPorId(Long id);
    List<MedicamentoDTO> listarActivos();
    void eliminar(Long id, Long estadoId);

}
