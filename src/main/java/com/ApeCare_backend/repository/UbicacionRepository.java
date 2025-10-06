package com.ApeCare_backend.repository;

import com.ApeCare_backend.entity.Ubicacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UbicacionRepository extends JpaRepository<Ubicacion, Long> {
        List<Ubicacion> findByEstadoNombreIgnoreCase(String estado);
}
