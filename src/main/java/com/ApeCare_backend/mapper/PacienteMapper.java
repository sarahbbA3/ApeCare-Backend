package com.ApeCare_backend.mapper;

import com.ApeCare_backend.dto.PacienteDTO;
import com.ApeCare_backend.entity.Estado;
import com.ApeCare_backend.entity.Paciente;
import com.ApeCare_backend.entity.TipoPaciente;

import java.time.LocalDate;

public class PacienteMapper {

    public static PacienteDTO toDTO(Paciente paciente) {
        PacienteDTO dto = new PacienteDTO();
        dto.setId(paciente.getId());
        dto.setNombre(paciente.getNombre());
        dto.setEdad(paciente.getEdad());
        dto.setCedula(paciente.getCedula());
        dto.setNumeroCarnet(paciente.getNumeroCarnet());

        LocalDate fechaRegistro = paciente.getFechaRegistro().toLocalDate();
        LocalDate fechaActualizacion = paciente.getFechaActualizacion() != null
                ? paciente.getFechaActualizacion().toLocalDate()
                : fechaRegistro;

        dto.setFechaRegistro(fechaRegistro);
        dto.setFechaActualizacion(fechaActualizacion);
        dto.setTipoPacienteId(paciente.getTipoPaciente().getId());

        return dto;
    }

    public static Paciente toEntity(PacienteDTO dto, TipoPaciente tipo, Estado estado) {
        Paciente paciente = new Paciente();
        paciente.setId(dto.getId());
        paciente.setNombre(dto.getNombre());
        paciente.setEdad(dto.getEdad());
        paciente.setCedula(dto.getCedula());
        paciente.setNumeroCarnet(dto.getNumeroCarnet());
        paciente.setTipoPaciente(tipo);
        paciente.setEstado(estado);
        return paciente;
    }

}
