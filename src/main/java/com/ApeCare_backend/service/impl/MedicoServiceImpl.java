package com.ApeCare_backend.service.impl;

import com.ApeCare_backend.dto.MedicoDTO;
import com.ApeCare_backend.entity.Especialidad;
import com.ApeCare_backend.entity.Estado;
import com.ApeCare_backend.entity.Medico;
import com.ApeCare_backend.entity.TandaLabor;
import com.ApeCare_backend.mapper.MedicoMapper;
import com.ApeCare_backend.repository.EspecialidadRepository;
import com.ApeCare_backend.repository.EstadoRepository;
import com.ApeCare_backend.repository.MedicoRepository;
import com.ApeCare_backend.repository.TandaLaborRepository;
import com.ApeCare_backend.service.MedicoService;
import com.ApeCare_backend.util.ValidacionCedula;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MedicoServiceImpl implements MedicoService {

    private final MedicoRepository medicoRepo;
    private final EstadoRepository estadoRepo;
    private final EspecialidadRepository especialidadRepo;
    private final TandaLaborRepository tandaRepo;

    @Override
    public MedicoDTO crear(MedicoDTO dto) {
        if (!ValidacionCedula.esCedulaValida(dto.getCedula())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cédula inválida");
        }

        if (medicoRepo.findByCedula(dto.getCedula()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ya existe un médico con esta cédula");
        }

        Estado estado = estadoRepo.findById(1L).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Estado ACTIVO no encontrado"));

        Especialidad especialidad = especialidadRepo.findById(dto.getEspecialidadId()).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Especialidad no encontrada"));

        TandaLabor tanda = tandaRepo.findById(dto.getTandaLaborId()).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Tanda laboral no encontrada"));

        Medico medico = MedicoMapper.toEntity(dto, especialidad, tanda, estado);
        return MedicoMapper.toDTO(medicoRepo.save(medico));
    }

    @Override
    public List<MedicoDTO> listarActivos() {
        return medicoRepo.findByEstadoNombreIgnoreCase("ACTIVO").stream()
                .map(MedicoMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public MedicoDTO editar(Long id, MedicoDTO dto) {
        if (!ValidacionCedula.esCedulaValida(dto.getCedula())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cédula inválida");
        }

        Medico medico = medicoRepo.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Médico no encontrado"));

        Especialidad especialidad = especialidadRepo.findById(dto.getEspecialidadId()).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Especialidad no encontrada"));

        TandaLabor tanda = tandaRepo.findById(dto.getTandaLaborId()).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Tanda laboral no encontrada"));

        medico.setNombre(dto.getNombre());
        medico.setCedula(dto.getCedula());
        medico.setEspecialidad(especialidad);
        medico.setTandaLabor(tanda);
        medico.setFechaCreacion(LocalDateTime.now());

        return MedicoMapper.toDTO(medicoRepo.save(medico));
    }

    @Override
    public void eliminar(Long id, Long estadoId) {
        Medico medico = medicoRepo.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Médico no encontrado"));

        Estado estado = estadoRepo.findById(estadoId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Estado destino no encontrado"));

        medico.setEstado(estado);
        medicoRepo.save(medico);
    }

}
