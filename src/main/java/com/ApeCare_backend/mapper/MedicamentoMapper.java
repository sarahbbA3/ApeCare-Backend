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

        Ubicacion ubicacion = medicamento.getUbicacion();
        Celda celda = ubicacion.getCelda();
        Tramo tramo = celda.getTramo();
        Estante estante = tramo.getEstante();

        MedicamentoDTO dto = new MedicamentoDTO();
        dto.setId(medicamento.getId());
        dto.setDescripcion(medicamento.getDescripcion());
        dto.setDosis(medicamento.getDosis());
        dto.setCantidadDisponible(medicamento.getCantidadDisponible());
        dto.setFechaVencimiento(medicamento.getFechaVencimiento());
        dto.setFechaCreacion(fechaCreacion);
        dto.setFechaActualizacion(fechaActualizacion);

        dto.setTipoFarmacoId(medicamento.getTipoFarmaco().getId());
        dto.setUbicacionId(ubicacion.getId());
        dto.setMarcaId(medicamento.getMarca().getId());
        dto.setTipoFarmacoNombre(medicamento.getTipoFarmaco().getNombre());
        dto.setMarcaNombre(medicamento.getMarca().getNombre());
        dto.setUbicacionNombre(ubicacion.getNombre());
        dto.setEstanteNombre(estante.getNombre());
        dto.setTramoNombre(tramo.getNombre());
        dto.setCeldaNombre(celda.getNombre());

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
