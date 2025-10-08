package com.ApeCare_backend.service.impl;

import com.ApeCare_backend.dto.EspecialidadDTO;
import com.ApeCare_backend.entity.Especialidad;
import com.ApeCare_backend.entity.Estado;
import com.ApeCare_backend.mapper.EspecialidadMapper;
import com.ApeCare_backend.repository.EspecialidadRepository;
import com.ApeCare_backend.repository.EstadoRepository;
import com.ApeCare_backend.service.EspecialidadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EspecialidadServiceImpl implements EspecialidadService {

    private final EspecialidadRepository especialidadRepo;
    private final EstadoRepository estadoRepo;

    @Override
    public EspecialidadDTO crear(EspecialidadDTO dto) {
        Estado estado = estadoRepo.findById(1L).orElseThrow();
        Especialidad especialidad = EspecialidadMapper.toEntity(dto, estado);
        return EspecialidadMapper.toDTO(especialidadRepo.save(especialidad));
    }

    @Override
    public List<EspecialidadDTO> listarActivos() {
        return especialidadRepo.findByEstadoNombreIgnoreCase("ACTIVO").stream()
                .map(EspecialidadMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void eliminar(Long id, Long estadoId) {
        Especialidad especialidad = especialidadRepo.findById(id).orElseThrow();
        Estado estado = estadoRepo.findById(estadoId).orElseThrow();
        especialidad.setEstado(estado);
        especialidadRepo.save(especialidad);
    }

}
