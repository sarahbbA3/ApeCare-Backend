package com.ApeCare_backend.repository;

import com.ApeCare_backend.entity.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RolRepository extends JpaRepository<Rol, Long> {
    List<Rol> findByEstadoNombreIgnoreCase(String estado);
}
