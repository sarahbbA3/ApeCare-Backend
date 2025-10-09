package com.ApeCare_backend.service.impl;

import com.ApeCare_backend.dto.TipoPacienteDTO;
import com.ApeCare_backend.entity.Estado;
import com.ApeCare_backend.entity.TipoPaciente;
import com.ApeCare_backend.mapper.TipoPacienteMapper;
import com.ApeCare_backend.repository.EstadoRepository;
import com.ApeCare_backend.repository.TipoPacienteRepository;
import com.ApeCare_backend.service.TipoPacienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TipoPacienteServiceImpl implements TipoPacienteService {

    private final TipoPacienteRepository tipoRepo;
    private final EstadoRepository estadoRepo;

    @Override
    public TipoPacienteDTO crear(TipoPacienteDTO dto) {
        Estado estado = estadoRepo.findById(1L).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Estado ACTIVO no encontrado"));

        TipoPaciente tipo = TipoPacienteMapper.toEntity(dto, estado);
        return TipoPacienteMapper.toDTO(tipoRepo.save(tipo));
    }

    @Override
    public List<TipoPacienteDTO> listarActivos() {
        return tipoRepo.findByEstadoNombreIgnoreCase("ACTIVO").stream()
                .map(TipoPacienteMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public TipoPacienteDTO editar(TipoPacienteDTO dto) {
        TipoPaciente existente = tipoRepo.findById(dto.getId()).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Tipo de paciente no encontrado"));

        existente.setNombre(dto.getNombre());
        existente.setDescripcion(dto.getDescripcion());

        return TipoPacienteMapper.toDTO(tipoRepo.save(existente));
    }

    @Override
    public void eliminar(Long id, Long estadoId) {
        TipoPaciente tipo = tipoRepo.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Tipo de paciente no encontrado"));

        Estado estado = estadoRepo.findById(estadoId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Estado destino no encontrado"));

        tipo.setEstado(estado);
        tipoRepo.save(tipo);
    }

}
