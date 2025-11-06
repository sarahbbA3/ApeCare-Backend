package com.ApeCare_backend.dto;

import com.ApeCare_backend.entity.Usuario;

public class LoginResponse {
    private Long id;
    private String nombre;
    private String correo;
    private String rol;

    public LoginResponse(Usuario usuario) {
        this.id = usuario.getId();
        this.nombre = usuario.getNombre();
        this.correo = usuario.getCorreo();
        this.rol = usuario.getRol() != null ? usuario.getRol().getNombre() : null;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public String getRol() {
        return rol;
    }

}
