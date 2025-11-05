package com.ApeCare_backend.service.impl;

import com.ApeCare_backend.dto.PacienteDTO;
import com.ApeCare_backend.entity.Estado;
import com.ApeCare_backend.entity.Paciente;
import com.ApeCare_backend.entity.TipoPaciente;
import com.ApeCare_backend.mapper.PacienteMapper;
import com.ApeCare_backend.repository.EstadoRepository;
import com.ApeCare_backend.repository.PacienteRepository;
import com.ApeCare_backend.repository.TipoPacienteRepository;
import com.ApeCare_backend.service.PacienteService;
import com.ApeCare_backend.util.ValidacionCedula;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PacienteServiceImpl implements PacienteService {

    private final PacienteRepository pacienteRepo;
    private final EstadoRepository estadoRepo;
    private final TipoPacienteRepository tipoRepo;

    @Override
    public PacienteDTO crear(PacienteDTO dto) {
        if (!ValidacionCedula.esCedulaValida(dto.getCedula())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cédula inválida");
        }

        if (dto.getEdad() != null && dto.getEdad() < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La edad no puede ser negativa");
        }

        if (pacienteRepo.findByCedula(dto.getCedula()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ya existe un paciente con esta cédula");
        }

        Estado estado = estadoRepo.findById(1L).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Estado ACTIVO no encontrado"));

        TipoPaciente tipo = tipoRepo.findById(dto.getTipoPacienteId()).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Tipo de paciente no encontrado"));

        Paciente paciente = PacienteMapper.toEntity(dto, tipo, estado);
        return PacienteMapper.toDTO(pacienteRepo.save(paciente));
    }

    @Override
    public List<PacienteDTO> listarActivos() {
        return pacienteRepo.findByEstadoNombreIgnoreCase("ACTIVO").stream()
                .map(PacienteMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PacienteDTO editar(Long id, PacienteDTO dto) {
        Paciente existente = pacienteRepo.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Paciente no encontrado"));

        if (!ValidacionCedula.esCedulaValida(dto.getCedula())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cédula inválida");
        }

        if (dto.getEdad() != null && dto.getEdad() < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La edad no puede ser negativa");
        }

        if (!dto.getCedula().equals(existente.getCedula()) &&
                pacienteRepo.findByCedula(dto.getCedula()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ya existe un paciente con esta cédula");
        }

        TipoPaciente tipo = tipoRepo.findById(dto.getTipoPacienteId()).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Tipo de paciente no encontrado"));

        existente.setNombre(dto.getNombre());
        existente.setCedula(dto.getCedula());
        existente.setNumeroCarnet(dto.getNumeroCarnet());
        existente.setTipoPaciente(tipo);
        existente.setEdad(dto.getEdad());

        return PacienteMapper.toDTO(pacienteRepo.save(existente));
    }

    @Override
    public void eliminar(Long id, Long estadoId) {
        Paciente paciente = pacienteRepo.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Paciente no encontrado"));

        Estado estado = estadoRepo.findById(estadoId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Estado destino no encontrado"));

        paciente.setEstado(estado);
        pacienteRepo.save(paciente);
    }
}