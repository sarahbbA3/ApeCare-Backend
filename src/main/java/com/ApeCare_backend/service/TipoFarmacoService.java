package com.ApeCare_backend.service;

import com.ApeCare_backend.dto.TipoFarmacoDTO;

import java.util.List;

public interface TipoFarmacoService {

    TipoFarmacoDTO crear(TipoFarmacoDTO dto);
    List<TipoFarmacoDTO> listarActivos();
    TipoFarmacoDTO editar(TipoFarmacoDTO dto);
    void eliminar(Long id, Long estadoId);
}
