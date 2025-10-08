package com.ApeCare_backend.service.impl;

import com.ApeCare_backend.dto.MedicamentoSuministradoDTO;
import com.ApeCare_backend.entity.Estado;
import com.ApeCare_backend.entity.Medicamento;
import com.ApeCare_backend.entity.MedicamentoSuministrado;
import com.ApeCare_backend.entity.RegistroVisita;
import com.ApeCare_backend.mapper.MedicamentoSuministradoMapper;
import com.ApeCare_backend.repository.EstadoRepository;
import com.ApeCare_backend.repository.MedicamentoRepository;
import com.ApeCare_backend.repository.MedicamentoSuministradoRepository;
import com.ApeCare_backend.repository.RegistroVisitaRepository;
import com.ApeCare_backend.service.MedicamentoSuministradoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MedicamentoSuministradoServiceImpl implements MedicamentoSuministradoService {

    private final MedicamentoSuministradoRepository repo;
    private final EstadoRepository estadoRepo;
    private final MedicamentoRepository medicamentoRepo;
    private final RegistroVisitaRepository visitaRepo;

    @Override
    public MedicamentoSuministradoDTO crear(MedicamentoSuministradoDTO dto) {
        Estado estado = estadoRepo.findById(1L).orElseThrow();
        Medicamento medicamento = medicamentoRepo.findById(dto.getMedicamentoId()).orElseThrow();
        RegistroVisita visita = visitaRepo.findById(dto.getVisitaId()).orElseThrow();

        MedicamentoSuministrado entity = MedicamentoSuministradoMapper.toEntity(dto, medicamento, visita, estado);
        return MedicamentoSuministradoMapper.toDTO(repo.save(entity));
    }

    @Override
    public List<MedicamentoSuministradoDTO> listarActivos() {
        return repo.findByEstadoNombreIgnoreCase("ACTIVO").stream()
                .map(MedicamentoSuministradoMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void eliminar(Long id, Long estadoId) {
        MedicamentoSuministrado entity = repo.findById(id).orElseThrow();
        Estado estado = estadoRepo.findById(estadoId).orElseThrow();
        entity.setEstado(estado);
        repo.save(entity);
    }

}
