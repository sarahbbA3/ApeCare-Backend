package com.ApeCare_backend.repository;

import com.ApeCare_backend.entity.Sintoma;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SintomaRepository extends JpaRepository<Sintoma, Long> {
    List<Sintoma> findByEstadoNombreIgnoreCase(String estado);
}
