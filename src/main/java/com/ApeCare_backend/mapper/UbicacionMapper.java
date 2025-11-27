package com.ApeCare_backend.mapper;

import com.ApeCare_backend.dto.UbicacionDTO;
import com.ApeCare_backend.entity.*;

import java.time.LocalDate;

public class UbicacionMapper {

    public static UbicacionDTO toDTO(Ubicacion ubicacion) {
        Celda celda = ubicacion.getCelda();
        Tramo tramo = celda.getTramo();
        Estante estante = tramo.getEstante();
        TipoFarmaco tipo = ubicacion.getTipoFarmaco();

        LocalDate fc = ubicacion.getFechaCreacion().toLocalDate();
        LocalDate fa = ubicacion.getFechaActualizacion() != null
                ? ubicacion.getFechaActualizacion().toLocalDate()
                : fc;

        return new UbicacionDTO(
                ubicacion.getId(),
                ubicacion.getNombre(),
                celda.getId(),
                celda.getNombre(),
                tramo.getNombre(),
                estante.getNombre(),
                tipo.getId(),
                tipo.getNombre(),
                ubicacion.getEstado().getId(),
                fc,
                fa
        );
    }

    public static Ubicacion toEntity(UbicacionDTO dto, Celda celda, TipoFarmaco tipo, Estado estado) {
        Ubicacion u = new Ubicacion();
        u.setId(dto.getId());
        u.setNombre(dto.getNombre());
        u.setCelda(celda);
        u.setTipoFarmaco(tipo);
        u.setEstado(estado);
        return u;
    }

}
