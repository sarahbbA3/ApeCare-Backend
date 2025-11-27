package com.ApeCare_backend.service.impl;

import com.ApeCare_backend.dto.EstanteDTO;
import com.ApeCare_backend.entity.Estado;
import com.ApeCare_backend.entity.Estante;
import com.ApeCare_backend.mapper.EstanteMapper;
import com.ApeCare_backend.repository.EstadoRepository;
import com.ApeCare_backend.repository.EstanteRepository;
import com.ApeCare_backend.service.EstanteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EstanteServiceImpl implements EstanteService {

    private final EstanteRepository estanteRepo;
    private final EstadoRepository estadoRepo;

    @Override
    public EstanteDTO crear(EstanteDTO dto) {
        Estado estado = estadoRepo.findById(dto.getEstadoId())
                .orElseThrow(() -> new RuntimeException("Estado no encontrado"));
        Estante estante = EstanteMapper.toEntity(dto, estado);
        return EstanteMapper.toDTO(estanteRepo.save(estante));
    }

    @Override
    public List<EstanteDTO> listarActivos() {
        return estanteRepo.findByEstadoNombreIgnoreCase("ACTIVO").stream()
                .map(EstanteMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public EstanteDTO editar(EstanteDTO dto) {
        Estante existente = estanteRepo.findById(dto.getId())
                .orElseThrow(() -> new RuntimeException("Estante no encontrado"));

        Estado estado = estadoRepo.findById(dto.getEstadoId())
                .orElseThrow(() -> new RuntimeException("Estado no encontrado"));

        existente.setNombre(dto.getNombre());
        existente.setEstado(estado);

        return EstanteMapper.toDTO(estanteRepo.save(existente));
    }

    @Override
    public void eliminar(Long id, Long estadoId) {
        Estante estante = estanteRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Estante no encontrado"));
        Estado estado = estadoRepo.findById(estadoId)
                .orElseThrow(() -> new RuntimeException("Estado no encontrado"));
        estante.setEstado(estado);
        estanteRepo.save(estante);
    }

}
