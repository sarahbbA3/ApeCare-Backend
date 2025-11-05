package com.ApeCare_backend.mapper;

import com.ApeCare_backend.dto.MedicamentoDTO;
import com.ApeCare_backend.entity.*;

import java.time.LocalDate;

public class MedicamentoMapper {

    public static MedicamentoDTO toDTO(Medicamento medicamento) {
        LocalDate fechaCreacion = medicamento.getFechaCreacion().toLocalDate();
        LocalDate fechaActualizacion = medicamento.getFechaActualizacion() != null
                ? medicamento.getFechaActualizacion().toLocalDate()
                : fechaCreacion;

        MedicamentoDTO dto = new MedicamentoDTO();
        dto.setId(medicamento.getId());
        dto.setDescripcion(medicamento.getDescripcion());
        dto.setDosis(medicamento.getDosis());
        dto.setCantidadDisponible(medicamento.getCantidadDisponible());
        dto.setFechaVencimiento(medicamento.getFechaVencimiento());
        dto.setFechaCreacion(fechaCreacion);
        dto.setFechaActualizacion(fechaActualizacion);

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
