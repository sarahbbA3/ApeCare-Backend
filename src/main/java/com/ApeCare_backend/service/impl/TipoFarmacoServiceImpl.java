package com.ApeCare_backend.service.impl;

import com.ApeCare_backend.dto.TipoFarmacoDTO;
import com.ApeCare_backend.entity.Estado;
import com.ApeCare_backend.entity.TipoFarmaco;
import com.ApeCare_backend.mapper.TipoFarmacoMapper;
import com.ApeCare_backend.repository.EstadoRepository;
import com.ApeCare_backend.repository.TipoFarmacoRepository;
import com.ApeCare_backend.service.TipoFarmacoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TipoFarmacoServiceImpl implements TipoFarmacoService {

    private final TipoFarmacoRepository tipoRepo;
    private final EstadoRepository estadoRepo;

    @Override
    public TipoFarmacoDTO crear(TipoFarmacoDTO dto) {
        Estado estado = estadoRepo.findById(dto.getEstadoId())
                .orElseThrow(() -> new RuntimeException("Estado no encontrado"));
        TipoFarmaco tipo = TipoFarmacoMapper.toEntity(dto, estado);
        return TipoFarmacoMapper.toDTO(tipoRepo.save(tipo));
    }

    @Override
    public List<TipoFarmacoDTO> listarActivos() {
        return tipoRepo.findByEstadoNombreIgnoreCase("ACTIVO").stream()
                .map(TipoFarmacoMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public TipoFarmacoDTO editar(TipoFarmacoDTO dto) {
        TipoFarmaco existente = tipoRepo.findById(dto.getId())
                .orElseThrow(() -> new RuntimeException("Tipo de fármaco no encontrado"));

        Estado estado = estadoRepo.findById(dto.getEstadoId())
                .orElseThrow(() -> new RuntimeException("Estado no encontrado"));

        existente.setNombre(dto.getNombre());
        existente.setDescripcion(dto.getDescripcion());
        existente.setEstado(estado);

        return TipoFarmacoMapper.toDTO(tipoRepo.save(existente));
    }

    @Override
    public void eliminar(Long id, Long estadoId) {
        TipoFarmaco tipo = tipoRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Tipo de fármaco no encontrado"));
        Estado estado = estadoRepo.findById(estadoId)
                .orElseThrow(() -> new RuntimeException("Estado no encontrado"));
        tipo.setEstado(estado);
        tipoRepo.save(tipo);
    }

}
