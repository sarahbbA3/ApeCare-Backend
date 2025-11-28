package com.ApeCare_backend.repository;

import com.ApeCare_backend.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    List<Usuario> findByEstadoNombreIgnoreCase(String estado);
    Optional<Usuario> findByCorreo(String correo);
    Long countByRolId(Long rolId);
}
