package com.ApeCare_backend.mapper;

import com.ApeCare_backend.dto.TandaLaborDTO;
import com.ApeCare_backend.entity.Estado;
import com.ApeCare_backend.entity.TandaLabor;

import java.time.LocalDate;

public class TandaLaborMapper {

    public static TandaLaborDTO toDTO(TandaLabor tanda, Integer medicosAsignados) {
        TandaLaborDTO dto = new TandaLaborDTO();
        dto.setId(tanda.getId());
        dto.setNombre(tanda.getNombre());
        dto.setDescripcion(tanda.getDescripcion());
        dto.setFechaCreacion(tanda.getFechaCreacion().toLocalDate());

        LocalDate fechaActualizacion = tanda.getFechaActualizacion() != null
                ? tanda.getFechaActualizacion().toLocalDate()
                : tanda.getFechaCreacion().toLocalDate();

        dto.setFechaActualizacion(fechaActualizacion);
        dto.setMedicosAsignados(medicosAsignados);

        return dto;
    }

    public static TandaLabor toEntity(TandaLaborDTO dto, Estado estado) {
        TandaLabor tanda = new TandaLabor();
        tanda.setId(dto.getId());
        tanda.setNombre(dto.getNombre());
        tanda.setDescripcion(dto.getDescripcion());
        tanda.setEstado(estado);
        return tanda;
    }

}
