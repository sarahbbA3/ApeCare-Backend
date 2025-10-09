package com.ApeCare_backend.service;

import com.ApeCare_backend.dto.MedicoDTO;

import java.util.List;

public interface MedicoService {

    MedicoDTO crear(MedicoDTO dto);
    List<MedicoDTO> listarActivos();
    MedicoDTO editar(Long id, MedicoDTO dto);
    void eliminar(Long id, Long estadoId);

}
