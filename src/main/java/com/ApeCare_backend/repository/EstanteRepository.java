package com.ApeCare_backend.repository;

import com.ApeCare_backend.entity.Estante;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EstanteRepository extends JpaRepository<Estante, Long> {
    List<Estante> findByEstadoNombreIgnoreCase(String estado);
}
