package com.ApeCare_backend.mapper;

import com.ApeCare_backend.dto.MedicoDTO;
import com.ApeCare_backend.entity.*;

import java.time.LocalDate;

public class MedicoMapper {

    public static MedicoDTO toDTO(Medico medico) {
        MedicoDTO dto = new MedicoDTO();
        dto.setId(medico.getId());
        dto.setNombre(medico.getNombre());
        dto.setCedula(medico.getCedula());
        dto.setEspecialidadId(medico.getEspecialidad().getId());
        dto.setTandaLaborId(medico.getTandaLabor().getId());
        dto.setUsuarioId(
                medico.getUsuario() != null ? medico.getUsuario().getId() : null
        );
        dto.setFechaCreacion(medico.getFechaCreacion().toLocalDate());

        LocalDate fechaActualizacion = medico.getFechaActualizacion() != null
                ? medico.getFechaActualizacion().toLocalDate()
                : medico.getFechaCreacion().toLocalDate();

        dto.setFechaActualizacion(fechaActualizacion);
        return dto;
    }

    public static Medico toEntity(MedicoDTO dto, Especialidad especialidad, TandaLabor tanda, Estado estado, Usuario usuario) {
        Medico medico = new Medico();
        medico.setId(dto.getId());
        medico.setNombre(dto.getNombre());
        medico.setCedula(dto.getCedula());
        medico.setEspecialidad(especialidad);
        medico.setTandaLabor(tanda);
        medico.setEstado(estado);
        medico.setUsuario(usuario);
        return medico;
    }

}
