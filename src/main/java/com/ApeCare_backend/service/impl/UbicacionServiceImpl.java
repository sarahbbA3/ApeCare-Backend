package com.ApeCare_backend.service.impl;

import com.ApeCare_backend.dto.UbicacionDTO;
import com.ApeCare_backend.entity.Celda;
import com.ApeCare_backend.entity.Estado;
import com.ApeCare_backend.entity.Ubicacion;
import com.ApeCare_backend.mapper.UbicacionMapper;
import com.ApeCare_backend.repository.CeldaRepository;
import com.ApeCare_backend.repository.EstadoRepository;
import com.ApeCare_backend.repository.UbicacionRepository;
import com.ApeCare_backend.service.UbicacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UbicacionServiceImpl implements UbicacionService {
    private final UbicacionRepository ubicacionRepo;
    private final EstadoRepository estadoRepo;
    private final CeldaRepository celdaRepo;

    @Override
    public UbicacionDTO crear(UbicacionDTO dto) {
        Estado estado = estadoRepo.findById(dto.getEstadoId())
                .orElseThrow(() -> new RuntimeException("Estado no encontrado"));
        Celda celda = celdaRepo.findById(dto.getCeldaId())
                .orElseThrow(() -> new RuntimeException("Celda no encontrada"));

        Ubicacion ubicacion = UbicacionMapper.toEntity(dto, celda, estado);
        return UbicacionMapper.toDTO(ubicacionRepo.save(ubicacion));
    }

    @Override
    public List<UbicacionDTO> listarActivos() {
        return ubicacionRepo.findByEstadoNombreIgnoreCase("ACTIVO").stream()
                .map(UbicacionMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UbicacionDTO editar(UbicacionDTO dto) {
        Ubicacion existente = ubicacionRepo.findById(dto.getId())
                .orElseThrow(() -> new RuntimeException("Ubicación no encontrada"));

        Estado estado = estadoRepo.findById(dto.getEstadoId())
                .orElseThrow(() -> new RuntimeException("Estado no encontrado"));
        Celda celda = celdaRepo.findById(dto.getCeldaId())
                .orElseThrow(() -> new RuntimeException("Celda no encontrada"));

        existente.setCelda(celda);
        existente.setEstado(estado);

        return UbicacionMapper.toDTO(ubicacionRepo.save(existente));
    }

    @Override
    public void eliminar(Long id, Long estadoId) {
        Ubicacion ubicacion = ubicacionRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Ubicación no encontrada"));
        Estado estado = estadoRepo.findById(estadoId)
                .orElseThrow(() -> new RuntimeException("Estado no encontrado"));
        ubicacion.setEstado(estado);
        ubicacionRepo.save(ubicacion);
    }

}
