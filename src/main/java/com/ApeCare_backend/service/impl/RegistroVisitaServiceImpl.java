package com.ApeCare_backend.service.impl;

import com.ApeCare_backend.dto.RegistroVisitaDTO;
import com.ApeCare_backend.entity.*;
import com.ApeCare_backend.mapper.RegistroVisitaMapper;
import com.ApeCare_backend.repository.*;
import com.ApeCare_backend.service.RegistroVisitaService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RegistroVisitaServiceImpl implements RegistroVisitaService {

    private final RegistroVisitaRepository visitaRepo;
    private final EstadoRepository estadoRepo;
    private final PacienteRepository pacienteRepo;
    private final MedicoRepository medicoRepo;
    private final SintomaRepository sintomaRepo;
    private final MedicamentoRepository medicamentoRepo;

    @Override
    public RegistroVisitaDTO crear(RegistroVisitaDTO dto) {
        if (dto.getFechaVisita().isAfter(LocalDate.now())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se permiten fechas futuras");
        }

        Estado estado = estadoRepo.findById(1L).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Estado ACTIVO no encontrado"));

        Paciente paciente = pacienteRepo.findById(dto.getPacienteId()).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Paciente no encontrado"));

        Medico medico = medicoRepo.findById(dto.getMedicoId()).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Médico no encontrado"));

        List<RegistroVisitaSintoma> sintomas = dto.getSintomasIds().stream()
                .map(id -> {
                    Sintoma sintoma = sintomaRepo.findById(id).orElseThrow(() ->
                            new ResponseStatusException(HttpStatus.NOT_FOUND, "Síntoma no encontrado: " + id));
                    RegistroVisitaSintoma rvs = new RegistroVisitaSintoma();
                    rvs.setSintoma(sintoma);
                    rvs.setEstado(estado);
                    return rvs;
                }).collect(Collectors.toList());

        List<MedicamentoSuministrado> medicamentos = dto.getMedicamentosIds().stream()
                .map(id -> {
                    Medicamento medicamento = medicamentoRepo.findById(id).orElseThrow(() ->
                            new ResponseStatusException(HttpStatus.NOT_FOUND, "Medicamento no encontrado: " + id));
                    MedicamentoSuministrado ms = new MedicamentoSuministrado();
                    ms.setMedicamento(medicamento);
                    ms.setEstado(estado);
                    return ms;
                }).collect(Collectors.toList());

        RegistroVisita visita = RegistroVisitaMapper.toEntity(dto, paciente, medico, estado, sintomas, medicamentos);

        sintomas.forEach(s -> s.setRegistroVisita(visita));
        medicamentos.forEach(m -> m.setVisita(visita));

        return RegistroVisitaMapper.toDTO(visitaRepo.save(visita));
    }

    @Override
    public List<RegistroVisitaDTO> listarActivos() {
        return visitaRepo.findByEstadoNombreIgnoreCase("ACTIVO").stream()
                .map(RegistroVisitaMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public RegistroVisitaDTO editar(Long id, RegistroVisitaDTO dto) {
        if (dto.getFechaVisita().isAfter(LocalDate.now())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se permiten fechas futuras");
        }

        RegistroVisita visita = visitaRepo.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Registro de visita no encontrado"));

        Paciente paciente = pacienteRepo.findById(dto.getPacienteId()).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Paciente no encontrado"));

        Medico medico = medicoRepo.findById(dto.getMedicoId()).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Médico no encontrado"));

        Estado estado = estadoRepo.findById(1L).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Estado ACTIVO no encontrado"));

        visita.setSintomas(new ArrayList<>());
        visita.setMedicamentosEntregados(new ArrayList<>());

        List<RegistroVisitaSintoma> sintomas = dto.getSintomasIds().stream()
                .map(sintomaId -> {
                    Sintoma sintoma = sintomaRepo.findById(sintomaId).orElseThrow(() ->
                            new ResponseStatusException(HttpStatus.NOT_FOUND, "Síntoma no encontrado: " + sintomaId));
                    RegistroVisitaSintoma rvs = new RegistroVisitaSintoma();
                    rvs.setSintoma(sintoma);
                    rvs.setEstado(estado);
                    rvs.setRegistroVisita(visita);
                    return rvs;
                }).collect(Collectors.toList());

        List<MedicamentoSuministrado> medicamentos = dto.getMedicamentosIds().stream()
                .map(medicamentoId -> {
                    Medicamento medicamento = medicamentoRepo.findById(medicamentoId).orElseThrow(() ->
                            new ResponseStatusException(HttpStatus.NOT_FOUND, "Medicamento no encontrado: " + medicamentoId));
                    MedicamentoSuministrado ms = new MedicamentoSuministrado();
                    ms.setMedicamento(medicamento);
                    ms.setEstado(estado);
                    ms.setVisita(visita);
                    return ms;
                }).collect(Collectors.toList());

        visita.setFechaVisita(dto.getFechaVisita().atTime(dto.getHoraVisita()));
        visita.setHoraVisita(dto.getHoraVisita());
        visita.setRecomendaciones(dto.getRecomendaciones());
        visita.setPaciente(paciente);
        visita.setMedico(medico);
        visita.setSintomas(sintomas);
        visita.setMedicamentosEntregados(medicamentos);

        return RegistroVisitaMapper.toDTO(visitaRepo.save(visita));
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