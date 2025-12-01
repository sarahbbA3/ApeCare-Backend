package com.ApeCare_backend.mapper;

import com.ApeCare_backend.dto.MedicamentoSuministradoDTO;
import com.ApeCare_backend.entity.Estado;
import com.ApeCare_backend.entity.Medicamento;
import com.ApeCare_backend.entity.MedicamentoSuministrado;
import com.ApeCare_backend.entity.RegistroVisita;

public class MedicamentoSuministradoMapper {

    public static MedicamentoSuministradoDTO toDTO(MedicamentoSuministrado entity) {
        MedicamentoSuministradoDTO dto = new MedicamentoSuministradoDTO();
        dto.setId(entity.getId());
        dto.setMedicamentoId(entity.getMedicamento().getId());
        dto.setVisitaId(entity.getVisita().getId());
        dto.setCantidadSuministrada(entity.getCantidadSuministrada());
        dto.setFechaCreacion(entity.getFechaCreacion().toLocalDate());
        dto.setFechaActualizacion(entity.getFechaActualizacion() != null
                ? entity.getFechaActualizacion().toLocalDate()
                : null);
        return dto;
    }

    public static MedicamentoSuministrado toEntity(MedicamentoSuministradoDTO dto, Medicamento medicamento, RegistroVisita visita, Estado estado) {
        MedicamentoSuministrado entity = new MedicamentoSuministrado();
        entity.setId(dto.getId());
        entity.setCantidadSuministrada(dto.getCantidadSuministrada());
        entity.setMedicamento(medicamento);
        entity.setVisita(visita);
        entity.setEstado(estado);
        return entity;
    }

}
