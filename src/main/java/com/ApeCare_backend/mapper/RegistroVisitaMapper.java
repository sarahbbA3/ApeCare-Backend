package com.ApeCare_backend.mapper;

import com.ApeCare_backend.dto.RegistroVisitaDTO;
import com.ApeCare_backend.entity.*;

import java.util.List;
import java.util.stream.Collectors;

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

        dto.setSintomasIds(
                visita.getSintomas().stream()
                        .map(s -> s.getSintoma().getId())
                        .collect(Collectors.toList())
        );

        dto.setMedicamentosIds(
                visita.getMedicamentosEntregados().stream()
                        .map(m -> m.getMedicamento().getId())
                        .collect(Collectors.toList())
        );

        return dto;
    }
    public static RegistroVisita toEntity(
            RegistroVisitaDTO dto,
            Paciente paciente,
            Medico medico,
            Estado estado,
            List<RegistroVisitaSintoma> sintomas,
            List<MedicamentoSuministrado> medicamentos
    ) {
        RegistroVisita visita = new RegistroVisita();
        visita.setId(dto.getId());
        visita.setHoraVisita(dto.getHoraVisita());
        visita.setFechaVisita(dto.getFechaVisita().atTime(dto.getHoraVisita()));
        visita.setRecomendaciones(dto.getRecomendaciones());
        visita.setPaciente(paciente);
        visita.setMedico(medico);
        visita.setEstado(estado);
        visita.setSintomas(sintomas);
        visita.setMedicamentosEntregados(medicamentos);

        return visita;
    }


}
