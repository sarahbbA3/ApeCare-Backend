package com.ApeCare_backend.mapper;

import com.ApeCare_backend.dto.MedicamentoDTO;
import com.ApeCare_backend.entity.*;

public class MedicamentoMapper {

    public static MedicamentoDTO toDTO(Medicamento medicamento) {
        MedicamentoDTO dto = new MedicamentoDTO();
        dto.setId(medicamento.getId());
        dto.setDescripcion(medicamento.getDescripcion());
        dto.setDosis(medicamento.getDosis());
        dto.setCantidadDisponible(medicamento.getCantidadDisponible());
        dto.setFechaVencimiento(medicamento.getFechaVencimiento());
        dto.setFechaCreacion(medicamento.getFechaCreacion().toLocalDate());

        dto.setTipoFarmacoId(medicamento.getTipoFarmaco().getId());
        dto.setUbicacionId(medicamento.getUbicacion().getId());
        dto.setMarcaId(medicamento.getMarca().getId());

        return dto;
    }

    public static Medicamento toEntity(MedicamentoDTO dto, TipoFarmaco tipoFarmaco, Ubicacion ubicacion, Marca marca, Estado estado) {
        Medicamento medicamento = new Medicamento();
        medicamento.setId(dto.getId());
        medicamento.setDescripcion(dto.getDescripcion());
        medicamento.setDosis(dto.getDosis());
        medicamento.setCantidadDisponible(dto.getCantidadDisponible());
        medicamento.setFechaVencimiento(dto.getFechaVencimiento());

        medicamento.setTipoFarmaco(tipoFarmaco);
        medicamento.setUbicacion(ubicacion);
        medicamento.setMarca(marca);
        medicamento.setEstado(estado);

        return medicamento;
    }

}
