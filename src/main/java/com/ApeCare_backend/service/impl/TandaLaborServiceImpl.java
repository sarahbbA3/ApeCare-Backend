package com.ApeCare_backend.service.impl;

import com.ApeCare_backend.dto.TandaLaborDTO;
import com.ApeCare_backend.entity.Estado;
import com.ApeCare_backend.entity.TandaLabor;
import com.ApeCare_backend.mapper.TandaLaborMapper;
import com.ApeCare_backend.repository.EstadoRepository;
import com.ApeCare_backend.repository.TandaLaborRepository;
import com.ApeCare_backend.service.TandaLaborService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TandaLaborServiceImpl implements TandaLaborService {

    private final TandaLaborRepository tandaRepo;
    private final EstadoRepository estadoRepo;

    @Override
    public TandaLaborDTO crear(TandaLaborDTO dto) {
        Estado estado = estadoRepo.findById(1L).orElseThrow();
        TandaLabor tanda = TandaLaborMapper.toEntity(dto, estado);
        return TandaLaborMapper.toDTO(tandaRepo.save(tanda));
    }

    @Override
    public List<TandaLaborDTO> listarActivos() {
        return tandaRepo.findByEstadoNombreIgnoreCase("ACTIVO").stream()
                .map(TandaLaborMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public TandaLaborDTO editar(Long id, TandaLaborDTO dto) {
        TandaLabor tanda = tandaRepo.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Tanda no encontrada"));

        tanda.setNombre(dto.getNombre());
        tanda.setDescripcion(dto.getDescripcion());
        tanda.setFechaCreacion(LocalDateTime.now());

        return TandaLaborMapper.toDTO(tandaRepo.save(tanda));
    }

    @Override
    public void eliminar(Long id, Long estadoId) {
        TandaLabor tanda = tandaRepo.findById(id).orElseThrow();
        Estado estado = estadoRepo.findById(estadoId).orElseThrow();
        tanda.setEstado(estado);
        tandaRepo.save(tanda);
    }

}
