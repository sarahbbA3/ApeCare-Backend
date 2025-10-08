package com.ApeCare_backend.service;

import com.ApeCare_backend.dto.MedicoDTO;

import java.util.List;

public interface MedicoService {

    MedicoDTO crear(MedicoDTO dto);
    List<MedicoDTO> listarActivos();
    void eliminar(Long id, Long estadoId);

}
