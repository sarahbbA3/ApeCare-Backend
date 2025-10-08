package com.ApeCare_backend.service.impl;

import com.ApeCare_backend.dto.RegistroVisitaSintomaDTO;
import com.ApeCare_backend.entity.Estado;
import com.ApeCare_backend.entity.RegistroVisita;
import com.ApeCare_backend.entity.RegistroVisitaSintoma;
import com.ApeCare_backend.entity.Sintoma;
import com.ApeCare_backend.mapper.RegistroVisitaSintomaMapper;
import com.ApeCare_backend.repository.EstadoRepository;
import com.ApeCare_backend.repository.RegistroVisitaRepository;
import com.ApeCare_backend.repository.RegistroVisitaSintomaRepository;
import com.ApeCare_backend.repository.SintomaRepository;
import com.ApeCare_backend.service.RegistroVisitaSintomaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RegistroVisitaSintomaServiceImpl implements RegistroVisitaSintomaService {

    private final RegistroVisitaSintomaRepository repo;
    private final EstadoRepository estadoRepo;
    private final RegistroVisitaRepository visitaRepo;
    private final SintomaRepository sintomaRepo;

    @Override
    public RegistroVisitaSintomaDTO crear(RegistroVisitaSintomaDTO dto) {
        Estado estado = estadoRepo.findById(1L).orElseThrow();
        RegistroVisita visita = visitaRepo.findById(dto.getRegistroVisitaId()).orElseThrow();
        Sintoma sintoma = sintomaRepo.findById(dto.getSintomaId()).orElseThrow();

        RegistroVisitaSintoma entity = RegistroVisitaSintomaMapper.toEntity(dto, visita, sintoma, estado);
        return RegistroVisitaSintomaMapper.toDTO(repo.save(entity));
    }

    @Override
    public List<RegistroVisitaSintomaDTO> listarActivos() {
        return repo.findByEstadoNombreIgnoreCase("ACTIVO").stream()
                .map(RegistroVisitaSintomaMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void eliminar(Long id, Long estadoId) {
        RegistroVisitaSintoma entity = repo.findById(id).orElseThrow();
        Estado estado = estadoRepo.findById(estadoId).orElseThrow();
        entity.setEstado(estado);
        repo.save(entity);
    }

}
