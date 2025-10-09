package com.ApeCare_backend.service;

import com.ApeCare_backend.dto.SintomaDTO;

import java.util.List;

public interface SintomaService {

    SintomaDTO crear(SintomaDTO dto);
    List<SintomaDTO> listarActivos();
    SintomaDTO editar(SintomaDTO dto);
    void eliminar(Long id, Long estadoId);

}
