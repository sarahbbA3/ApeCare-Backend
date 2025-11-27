package com.ApeCare_backend.service.impl;

import com.ApeCare_backend.dto.TramoDTO;
import com.ApeCare_backend.entity.Estado;
import com.ApeCare_backend.entity.Estante;
import com.ApeCare_backend.entity.Tramo;
import com.ApeCare_backend.mapper.TramoMapper;
import com.ApeCare_backend.repository.EstadoRepository;
import com.ApeCare_backend.repository.EstanteRepository;
import com.ApeCare_backend.repository.TramoRepository;
import com.ApeCare_backend.service.TramoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TramoServiceImpl implements TramoService {

    private final TramoRepository tramoRepo;
    private final EstadoRepository estadoRepo;
    private final EstanteRepository estanteRepo;

    @Override
    public TramoDTO crear(TramoDTO dto) {
        Estado estado = estadoRepo.findById(dto.getEstadoId())
                .orElseThrow(() -> new RuntimeException("Estado no encontrado"));
        Estante estante = estanteRepo.findById(dto.getEstanteId())
                .orElseThrow(() -> new RuntimeException("Estante no encontrado"));
        Tramo tramo = TramoMapper.toEntity(dto, estante, estado);
        return TramoMapper.toDTO(tramoRepo.save(tramo));
    }

    @Override
    public List<TramoDTO> listarActivos() {
        return tramoRepo.findByEstadoNombreIgnoreCase("ACTIVO").stream()
                .map(TramoMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public TramoDTO editar(TramoDTO dto) {
        Tramo existente = tramoRepo.findById(dto.getId())
                .orElseThrow(() -> new RuntimeException("Tramo no encontrado"));

        Estado estado = estadoRepo.findById(dto.getEstadoId())
                .orElseThrow(() -> new RuntimeException("Estado no encontrado"));
        Estante estante = estanteRepo.findById(dto.getEstanteId())
                .orElseThrow(() -> new RuntimeException("Estante no encontrado"));

        existente.setNombre(dto.getNombre());
        existente.setEstado(estado);
        existente.setEstante(estante);

        return TramoMapper.toDTO(tramoRepo.save(existente));
    }

    @Override
    public void eliminar(Long id, Long estadoId) {
        Tramo tramo = tramoRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Tramo no encontrado"));
        Estado estado = estadoRepo.findById(estadoId)
                .orElseThrow(() -> new RuntimeException("Estado no encontrado"));
        tramo.setEstado(estado);
        tramoRepo.save(tramo);
    }

}
