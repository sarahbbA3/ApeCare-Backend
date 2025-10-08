package com.ApeCare_backend.repository;

import com.ApeCare_backend.entity.RegistroVisita;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RegistroVisitaRepository extends JpaRepository<RegistroVisita, Long> {
    List<RegistroVisita> findByEstadoNombreIgnoreCase(String estado);
}
