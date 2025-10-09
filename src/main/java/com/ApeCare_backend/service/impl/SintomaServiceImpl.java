package com.ApeCare_backend.service.impl;

import com.ApeCare_backend.dto.SintomaDTO;
import com.ApeCare_backend.entity.Estado;
import com.ApeCare_backend.entity.Sintoma;
import com.ApeCare_backend.mapper.SintomaMapper;
import com.ApeCare_backend.repository.EstadoRepository;
import com.ApeCare_backend.repository.SintomaRepository;
import com.ApeCare_backend.service.SintomaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SintomaServiceImpl implements SintomaService {

    private final SintomaRepository sintomaRepo;
    private final EstadoRepository estadoRepo;

    @Override
    public SintomaDTO crear(SintomaDTO dto) {
        Estado estado = estadoRepo.findById(1L).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Estado ACTIVO no encontrado"));

        Sintoma sintoma = SintomaMapper.toEntity(dto, estado);
        return SintomaMapper.toDTO(sintomaRepo.save(sintoma));
    }

    @Override
    public List<SintomaDTO> listarActivos() {
        return sintomaRepo.findByEstadoNombreIgnoreCase("ACTIVO").stream()
                .map(SintomaMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public SintomaDTO editar(SintomaDTO dto) {
        Sintoma existente = sintomaRepo.findById(dto.getId()).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Síntoma no encontrado"));

        Estado estado = estadoRepo.findById(1L).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Estado ACTIVO no encontrado"));

        existente.setNombre(dto.getNombre());
        existente.setDescripcion(dto.getDescripcion());
        existente.setEstado(estado);

        return SintomaMapper.toDTO(sintomaRepo.save(existente));
    }

    @Override
    public void eliminar(Long id, Long estadoId) {
        Sintoma sintoma = sintomaRepo.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Síntoma no encontrado"));

        Estado estado = estadoRepo.findById(estadoId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Estado destino no encontrado"));

        sintoma.setEstado(estado);
        sintomaRepo.save(sintoma);
    }

}
