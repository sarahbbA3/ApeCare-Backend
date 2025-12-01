package com.ApeCare_backend.service;

import com.ApeCare_backend.dto.RegistroVisitaSintomaDTO;

import java.util.List;

public interface RegistroVisitaSintomaService {

    RegistroVisitaSintomaDTO crear(RegistroVisitaSintomaDTO dto);
    List<RegistroVisitaSintomaDTO> listarActivos();
    void eliminar(Long id, Long estadoId);
    RegistroVisitaSintomaDTO editar(Long id, RegistroVisitaSintomaDTO dto);

}
