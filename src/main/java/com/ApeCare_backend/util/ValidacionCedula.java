package com.ApeCare_backend.util;

public class ValidacionCedula {

    public static boolean esCedulaValida(String cedula) {
        if (cedula == null) return false;

        String limpia = cedula.replace("-", "").trim();
        if (limpia.length() != 11 || !limpia.matches("\\d{11}")) return false;

        int[] multiplicadores = {1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1};
        int total = 0;

        for (int i = 0; i < 11; i++) {
            int digito = Character.getNumericValue(limpia.charAt(i));
            int producto = digito * multiplicadores[i];

            if (producto < 10) {
                total += producto;
            } else {
                total += producto / 10 + producto % 10;
            }
        }

        return total % 10 == 0;
    }

}
