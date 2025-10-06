package com.ApeCare_backend.service.impl;

import com.ApeCare_backend.dto.UbicacionDTO;
import com.ApeCare_backend.entity.Estado;
import com.ApeCare_backend.entity.Ubicacion;
import com.ApeCare_backend.mapper.UbicacionMapper;
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

    @Override
    public UbicacionDTO crear(UbicacionDTO dto) {
        Estado estado = estadoRepo.findById(dto.getEstadoId())
                .orElseThrow(() -> new RuntimeException("Estado no encontrado"));
        Ubicacion ubicacion = UbicacionMapper.toEntity(dto, estado);
        return UbicacionMapper.toDTO(ubicacionRepo.save(ubicacion));
    }

    @Override
    public List<UbicacionDTO> listarActivos() {
        return ubicacionRepo.findByEstadoNombreIgnoreCase("ACTIVO").stream()
                .map(UbicacionMapper::toDTO)
                .collect(Collectors.toList());
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
