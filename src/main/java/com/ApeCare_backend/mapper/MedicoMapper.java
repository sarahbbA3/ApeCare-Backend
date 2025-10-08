package com.ApeCare_backend.mapper;

import com.ApeCare_backend.dto.MedicoDTO;
import com.ApeCare_backend.entity.Estado;
import com.ApeCare_backend.entity.Medico;

@Component
public class MedicoMapper {

    public MedicoDTO toDTO(Medico medico) {
        MedicoDTO dto = new MedicoDTO();
        dto.setId(medico.getId());
        dto.setNombre(medico.getNombre());
        dto.setCedula(medico.getCedula());
        dto.setEspecialidadId(medico.getEspecialidad().getId());
        dto.setTandaLaborId(medico.getTandaLabor().getId());
        dto.setFechaCreacion(medico.getFechaCreacion().toLocalDate());
        return dto;
    }

    public Medico toEntity(MedicoDTO dto, Especialidad especialidad, TandaLabor tandaLabor, Estado estado) {
        Medico medico = new Medico();
        medico.setId(dto.getId());
        medico.setNombre(dto.getNombre());
        medico.setCedula(dto.getCedula());
        medico.setEspecialidad(especialidad);
        medico.setTandaLabor(tandaLabor);
        medico.setEstado(estado);
        return medico;
    }
}
