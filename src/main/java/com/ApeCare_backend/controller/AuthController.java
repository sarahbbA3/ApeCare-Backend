package com.ApeCare_backend.controller;

import com.ApeCare_backend.dto.LoginRequest;
import com.ApeCare_backend.dto.LoginResponse;
import com.ApeCare_backend.entity.Usuario;
import com.ApeCare_backend.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/auth/")
@CrossOrigin("*")
public class AuthController {
    @Autowired
    private UsuarioRepository usuarioRepo;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        Usuario usuario = usuarioRepo.findByCorreo(request.getCorreo())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Correo no válido"));

        if (!usuario.getContrasena().equals(request.getContrasena())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Contraseña incorrecta");
        }

        return ResponseEntity.ok(new LoginResponse(usuario));
    }

}
