package com.ApeCare_backend.repository;

import com.ApeCare_backend.entity.Celda;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CeldaRepository extends JpaRepository<Celda, Long> {
    List<Celda> findByEstadoNombreIgnoreCase(String estado);
}
