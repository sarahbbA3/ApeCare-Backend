package com.ApeCare_backend.service;

import com.ApeCare_backend.dto.EstadoDTO;

import java.util.List;

public interface EstadoService {

        EstadoDTO crearEstado(EstadoDTO dto);
        List<EstadoDTO> listarEstados();
        EstadoDTO obtenerPorId(Long id);
        EstadoDTO actualizarEstado(Long id, EstadoDTO dto);
        void eliminarEstado(Long id);

}
