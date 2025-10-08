package com.ApeCare_backend.service.impl;

import com.ApeCare_backend.dto.RegistroVisitaDTO;
import com.ApeCare_backend.entity.Estado;
import com.ApeCare_backend.entity.Medico;
import com.ApeCare_backend.entity.Paciente;
import com.ApeCare_backend.entity.RegistroVisita;
import com.ApeCare_backend.mapper.RegistroVisitaMapper;
import com.ApeCare_backend.repository.EstadoRepository;
import com.ApeCare_backend.repository.MedicoRepository;
import com.ApeCare_backend.repository.PacienteRepository;
import com.ApeCare_backend.repository.RegistroVisitaRepository;
import com.ApeCare_backend.service.RegistroVisitaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RegistroVisitaServiceImpl implements RegistroVisitaService {

    private final RegistroVisitaRepository visitaRepo;
    private final EstadoRepository estadoRepo;
    private final PacienteRepository pacienteRepo;
    private final MedicoRepository medicoRepo;

    @Override
    public RegistroVisitaDTO crear(RegistroVisitaDTO dto) {
        Estado estado = estadoRepo.findById(1L).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Estado ACTIVO no encontrado"));

        Paciente paciente = pacienteRepo.findById(dto.getPacienteId()).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Paciente no encontrado"));

        Medico medico = medicoRepo.findById(dto.getMedicoId()).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Médico no encontrado"));

        RegistroVisita visita = RegistroVisitaMapper.toEntity(dto, paciente, medico, estado);
        return RegistroVisitaMapper.toDTO(visitaRepo.save(visita));
    }

    @Override
    public List<RegistroVisitaDTO> listarActivos() {
        return visitaRepo.findByEstadoNombreIgnoreCase("ACTIVO").stream()
                .map(RegistroVisitaMapper::toDTO)
                .collect(Collectors.toList());
    }
    @Override
    public void eliminar(Long id, Long estadoId) {
        RegistroVisita visita = visitaRepo.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Registro de visita no encontrado"));

        Estado estado = estadoRepo.findById(estadoId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Estado destino no encontrado"));

        visita.setEstado(estado);
        visitaRepo.save(visita);
    }

}
