package com.ApeCare_backend.service.impl;

import com.ApeCare_backend.dto.CeldaDTO;
import com.ApeCare_backend.entity.Celda;
import com.ApeCare_backend.entity.Estado;
import com.ApeCare_backend.entity.Tramo;
import com.ApeCare_backend.mapper.CeldaMapper;
import com.ApeCare_backend.repository.CeldaRepository;
import com.ApeCare_backend.repository.EstadoRepository;
import com.ApeCare_backend.repository.TramoRepository;
import com.ApeCare_backend.service.CeldaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CeldaServiceImpl implements CeldaService {
    private final CeldaRepository celdaRepo;
    private final EstadoRepository estadoRepo;
    private final TramoRepository tramoRepo;

    @Override
    public CeldaDTO crear(CeldaDTO dto) {
        Estado estado = estadoRepo.findById(dto.getEstadoId())
                .orElseThrow(() -> new RuntimeException("Estado no encontrado"));
        Tramo tramo = tramoRepo.findById(dto.getTramoId())
                .orElseThrow(() -> new RuntimeException("Tramo no encontrado"));
        Celda celda = CeldaMapper.toEntity(dto, tramo, estado);
        return CeldaMapper.toDTO(celdaRepo.save(celda));
    }

    @Override
    public List<CeldaDTO> listarActivos() {
        return celdaRepo.findByEstadoNombreIgnoreCase("ACTIVO").stream()
                .map(CeldaMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CeldaDTO editar(CeldaDTO dto) {
        Celda existente = celdaRepo.findById(dto.getId())
                .orElseThrow(() -> new RuntimeException("Celda no encontrada"));

        Estado estado = estadoRepo.findById(dto.getEstadoId())
                .orElseThrow(() -> new RuntimeException("Estado no encontrado"));
        Tramo tramo = tramoRepo.findById(dto.getTramoId())
                .orElseThrow(() -> new RuntimeException("Tramo no encontrado"));

        existente.setNombre(dto.getNombre());
        existente.setEstado(estado);
        existente.setTramo(tramo);

        return CeldaMapper.toDTO(celdaRepo.save(existente));
    }

    @Override
    public void eliminar(Long id, Long estadoId) {
        Celda celda = celdaRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Celda no encontrada"));
        Estado estado = estadoRepo.findById(estadoId)
                .orElseThrow(() -> new RuntimeException("Estado no encontrado"));
        celda.setEstado(estado);
        celdaRepo.save(celda);
    }
}
