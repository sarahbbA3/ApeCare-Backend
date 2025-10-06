package com.ApeCare_backend.service.impl;

import com.ApeCare_backend.dto.EstadoDTO;
import com.ApeCare_backend.entity.Estado;
import com.ApeCare_backend.mapper.EstadoMapper;
import com.ApeCare_backend.repository.EstadoRepository;
import com.ApeCare_backend.service.EstadoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EstadoServiceImpl implements EstadoService {

    private final EstadoRepository estadoRepo;

    @Override
    public EstadoDTO crearEstado(EstadoDTO dto) {
        Estado estado = EstadoMapper.toEntity(dto);
        Estado guardado = estadoRepo.save(estado);
        return EstadoMapper.toDTO(guardado);
    }

    @Override
    public List<EstadoDTO> listarEstados() {
        return estadoRepo.findAll().stream()
                .map(EstadoMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public EstadoDTO obtenerPorId(Long id) {
        Estado estado = estadoRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Estado no encontrado con ID: " + id));
        return EstadoMapper.toDTO(estado);
    }

    @Override
    public EstadoDTO actualizarEstado(Long id, EstadoDTO dto) {
        Estado estado = estadoRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Estado no encontrado con ID: " + id));
        estado.setNombre(dto.getNombre());
        estado.setDescripcion(dto.getDescripcion());
        return EstadoMapper.toDTO(estadoRepo.save(estado));
    }

    @Override
    public void eliminarEstado(Long id) {
        estadoRepo.deleteById(id);
    }

}
