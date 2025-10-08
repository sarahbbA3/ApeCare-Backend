package com.ApeCare_backend.repository;

import com.ApeCare_backend.entity.Especialidad;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EspecialidadRepository extends JpaRepository<Especialidad, Long> {
    List<Especialidad> findByEstadoNombreIgnoreCase(String estado);

}
