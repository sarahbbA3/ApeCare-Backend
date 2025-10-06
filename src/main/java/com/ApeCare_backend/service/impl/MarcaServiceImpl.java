package com.ApeCare_backend.service.impl;

import com.ApeCare_backend.dto.MarcaDTO;
import com.ApeCare_backend.entity.Estado;
import com.ApeCare_backend.entity.Marca;
import com.ApeCare_backend.mapper.MarcaMapper;
import com.ApeCare_backend.repository.EstadoRepository;
import com.ApeCare_backend.repository.MarcaRepository;
import com.ApeCare_backend.service.MarcaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MarcaServiceImpl implements MarcaService {

    private final MarcaRepository marcaRepo;
    private final EstadoRepository estadoRepo;

    @Override
    public MarcaDTO crear(MarcaDTO dto) {
        Estado estado = estadoRepo.findById(dto.getEstadoId())
                .orElseThrow(() -> new RuntimeException("Estado no encontrado"));
        Marca marca = MarcaMapper.toEntity(dto, estado);
        return MarcaMapper.toDTO(marcaRepo.save(marca));
    }

    @Override
    public List<MarcaDTO> listarActivos() {
        return marcaRepo.findByEstadoNombreIgnoreCase("ACTIVO").stream()
                .map(MarcaMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void eliminar(Long id, Long estadoId) {
        Marca marca = marcaRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Marca no encontrada"));
        Estado estado = estadoRepo.findById(estadoId)
                .orElseThrow(() -> new RuntimeException("Estado no encontrado"));
        marca.setEstado(estado);
        marcaRepo.save(marca);
    }


}
