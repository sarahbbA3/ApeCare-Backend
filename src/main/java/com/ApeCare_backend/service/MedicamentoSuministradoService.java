package com.ApeCare_backend.service;

import com.ApeCare_backend.dto.MedicamentoSuministradoDTO;

import java.util.List;

public interface MedicamentoSuministradoService {

    MedicamentoSuministradoDTO crear(MedicamentoSuministradoDTO dto);
    List<MedicamentoSuministradoDTO> listarActivos();
    void eliminar(Long id, Long estadoId);
    MedicamentoSuministradoDTO editar(Long id, MedicamentoSuministradoDTO dto);

}
