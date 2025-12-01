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

        List<MedicamentoSuministrado> medicamentos = dto.getMedicamentos().stream()
                .map(medDTO -> {
                    Medicamento medicamento = medicamentoRepo.findById(medDTO.getMedicamentoId())
                            .orElseThrow(() ->
                                    new ResponseStatusException(HttpStatus.NOT_FOUND,
                                            "Medicamento no encontrado: " + medDTO.getMedicamentoId()));

                    int cantidadSolicitada = medDTO.getCantidadSuministrada();

                    if (cantidadSolicitada <= 0) {
                        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                                "La cantidad suministrada debe ser mayor que 0");
                    }

                    if (cantidadSolicitada > medicamento.getCantidadDisponible()) {
                        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                                "Stock insuficiente de " + medicamento.getDescripcion());
                    }

                    medicamento.setCantidadDisponible(medicamento.getCantidadDisponible() - cantidadSolicitada);
                    medicamentoRepo.save(medicamento);

                    MedicamentoSuministrado ms = new MedicamentoSuministrado();
                    ms.setMedicamento(medicamento);
                    ms.setCantidadSuministrada(cantidadSolicitada);
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

        // Guardar lista original de medicamentos entregados
        List<MedicamentoSuministrado> originales = new ArrayList<>(visita.getMedicamentosEntregados());

        visita.getSintomas().clear();
        visita.getMedicamentosEntregados().clear();

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

        List<MedicamentoSuministrado> nuevos = dto.getMedicamentos().stream()
                .map(medDTO -> {
                    Medicamento medicamento = medicamentoRepo.findById(medDTO.getMedicamentoId())
                            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                    "Medicamento no encontrado: " + medDTO.getMedicamentoId()));

                    int cantidadNueva = medDTO.getCantidadSuministrada();
                    if (cantidadNueva <= 0) {
                        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La cantidad suministrada debe ser mayor que 0");
                    }

                    MedicamentoSuministrado previo = originales.stream()
                            .filter(ms -> ms.getMedicamento().getId().equals(medDTO.getMedicamentoId()))
                            .findFirst()
                            .orElse(null);

                    if (previo != null) {
                        int diferencia = cantidadNueva - previo.getCantidadSuministrada();
                        if (diferencia > 0 && diferencia > medicamento.getCantidadDisponible()) {
                            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                                    "Stock insuficiente de " + medicamento.getDescripcion());
                        }
                        medicamento.setCantidadDisponible(medicamento.getCantidadDisponible() - diferencia);
                    } else {
                        if (cantidadNueva > medicamento.getCantidadDisponible()) {
                            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                                    "Stock insuficiente de " + medicamento.getDescripcion());
                        }
                        medicamento.setCantidadDisponible(medicamento.getCantidadDisponible() - cantidadNueva);
                    }

                    medicamentoRepo.save(medicamento);

                    MedicamentoSuministrado ms = new MedicamentoSuministrado();
                    ms.setMedicamento(medicamento);
                    ms.setCantidadSuministrada(cantidadNueva);
                    ms.setEstado(estado);
                    ms.setVisita(visita);
                    return ms;
                }).collect(Collectors.toList());

        for (MedicamentoSuministrado previo : originales) {
            boolean sigue = nuevos.stream()
                    .anyMatch(ms -> ms.getMedicamento().getId().equals(previo.getMedicamento().getId()));
            if (!sigue) {
                Medicamento med = previo.getMedicamento();
                med.setCantidadDisponible(med.getCantidadDisponible() + previo.getCantidadSuministrada());
                medicamentoRepo.save(med);
            }
        }

        visita.setFechaVisita(dto.getFechaVisita().atTime(dto.getHoraVisita()));
        visita.setHoraVisita(dto.getHoraVisita());
        visita.setRecomendaciones(dto.getRecomendaciones());
        visita.setPaciente(paciente);
        visita.setMedico(medico);

        visita.getSintomas().addAll(sintomas);
        visita.getMedicamentosEntregados().addAll(nuevos);

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