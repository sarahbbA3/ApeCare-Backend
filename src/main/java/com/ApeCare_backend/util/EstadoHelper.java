package com.ApeCare_backend.util;

import com.ApeCare_backend.entity.Estado;

public class EstadoHelper {

    public static boolean estaActivo(Estado estado) {
        return estado != null && EstadoConst.ACTIVO.equalsIgnoreCase(estado.getNombre());
    }

    public static boolean estaEliminado(Estado estado) {
        return estado != null && EstadoConst.ELIMINADO.equalsIgnoreCase(estado.getNombre());
    }

    public static boolean estaInactivo(Estado estado) {
        return estado != null && EstadoConst.INACTIVO.equalsIgnoreCase(estado.getNombre());
    }

}
