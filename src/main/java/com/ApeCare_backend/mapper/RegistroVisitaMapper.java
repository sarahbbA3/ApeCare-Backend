package com.ApeCare_backend.mapper;

import com.ApeCare_backend.dto.RegistroVisitaDTO;
import com.ApeCare_backend.entity.Estado;
import com.ApeCare_backend.entity.Medico;
import com.ApeCare_backend.entity.Paciente;
import com.ApeCare_backend.entity.RegistroVisita;

public class RegistroVisitaMapper {

    public static RegistroVisitaDTO toDTO(RegistroVisita visita) {
        RegistroVisitaDTO dto = new RegistroVisitaDTO();
        dto.setId(visita.getId());
        dto.setFechaVisita(visita.getFechaVisita().toLocalDate());
        dto.setHoraVisita(visita.getHoraVisita());
        dto.setRecomendaciones(visita.getRecomendaciones());
        dto.setFechaRegistro(visita.getFechaVisita().toLocalDate());
        dto.setPacienteId(visita.getPaciente().getId());
        dto.setMedicoId(visita.getMedico().getId());
        dto.setFechaCreacion(visita.getFechaCreacion().toLocalDate());
        return dto;
    }

    public static RegistroVisita toEntity(RegistroVisitaDTO dto, Paciente paciente, Medico medico, Estado estado) {
        RegistroVisita visita = new RegistroVisita();
        visita.setId(dto.getId());
        visita.setHoraVisita(dto.getHoraVisita());
        visita.setFechaVisita(dto.getFechaVisita().atTime(dto.getHoraVisita()));
        visita.setRecomendaciones(dto.getRecomendaciones());
        visita.setPaciente(paciente);
        visita.setMedico(medico);
        visita.setEstado(estado);
        return visita;
    }

}
