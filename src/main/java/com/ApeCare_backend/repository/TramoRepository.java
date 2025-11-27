package com.ApeCare_backend.repository;

import com.ApeCare_backend.entity.Tramo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TramoRepository extends JpaRepository<Tramo, Long> {
    List<Tramo> findByEstadoNombreIgnoreCase(String estado);
}
