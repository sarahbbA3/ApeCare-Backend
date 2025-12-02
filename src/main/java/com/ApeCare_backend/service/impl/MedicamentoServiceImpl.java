package com.ApeCare_backend.service.impl;

import com.ApeCare_backend.dto.MedicamentoDTO;
import com.ApeCare_backend.entity.*;
import com.ApeCare_backend.mapper.MedicamentoMapper;
import com.ApeCare_backend.repository.*;
import com.ApeCare_backend.service.MedicamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MedicamentoServiceImpl implements MedicamentoService {

    private final MedicamentoRepository medicamentoRepo;
    private final EstadoRepository estadoRepo;
    private final TipoFarmacoRepository tipoRepo;
    private final UbicacionRepository ubicacionRepo;
    private final MarcaRepository marcaRepo;

    @Override
    public MedicamentoDTO crear(MedicamentoDTO dto) {
        Estado estado = estadoRepo.findById(1L).orElseThrow();
        TipoFarmaco tipo = tipoRepo.findById(dto.getTipoFarmacoId()).orElseThrow();
        Ubicacion ubicacion = ubicacionRepo.findById(dto.getUbicacionId()).orElseThrow();
        Marca marca = marcaRepo.findById(dto.getMarcaId()).orElseThrow();
        //no mas de tres por ubi
        long count = medicamentoRepo.countByUbicacionIdAndEstadoNombreIgnoreCase(ubicacion.getId(), "ACTIVO");
        if (count >= 3) {
            throw new RuntimeException("No se pueden agregar más de 3 medicamentos en esta ubicación");
        }
        Medicamento medicamento = MedicamentoMapper.toEntity(dto, tipo, ubicacion, marca, estado);
        return MedicamentoMapper.toDTO(medicamentoRepo.save(medicamento));
    }

    @Override
    public MedicamentoDTO editar(Long id, MedicamentoDTO dto) {
        Medicamento existente = medicamentoRepo.findById(id).orElseThrow();
        TipoFarmaco tipo = tipoRepo.findById(dto.getTipoFarmacoId()).orElseThrow();
        Ubicacion ubicacion = ubicacionRepo.findById(dto.getUbicacionId()).orElseThrow();
        Marca marca = marcaRepo.findById(dto.getMarcaId()).orElseThrow();
        //validación si se cambia de ubicación
        if (!existente.getUbicacion().getId().equals(ubicacion.getId())) {
            long count = medicamentoRepo.countByUbicacionIdAndEstadoNombreIgnoreCase(ubicacion.getId(), "ACTIVO");
            if (count >= 3) {
                throw new RuntimeException("No se pueden agregar más de 3 medicamentos en esta ubicación");
            }
        }

        existente.setDescripcion(dto.getDescripcion());
        existente.setDosis(dto.getDosis());
        existente.setCantidadDisponible(dto.getCantidadDisponible());
        existente.setFechaVencimiento(dto.getFechaVencimiento());
        existente.setTipoFarmaco(tipo);
        existente.setUbicacion(ubicacion);
        existente.setMarca(marca);

        return MedicamentoMapper.toDTO(medicamentoRepo.save(existente));
    }

    @Override
    public MedicamentoDTO buscarPorId(Long id) {
        Medicamento medicamento = medicamentoRepo.findById(id).orElseThrow();
        return MedicamentoMapper.toDTO(medicamento);
    }

    @Override
    public List<MedicamentoDTO> listarActivos() {
        return medicamentoRepo.findByEstadoNombreIgnoreCase("ACTIVO").stream()
                .map(MedicamentoMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void eliminar(Long id, Long estadoId) {
        Medicamento medicamento = medicamentoRepo.findById(id).orElseThrow();
        Estado estado = estadoRepo.findById(estadoId).orElseThrow();
        medicamento.setEstado(estado);
        medicamentoRepo.save(medicamento);
    }

}
