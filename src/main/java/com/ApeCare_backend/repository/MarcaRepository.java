package com.ApeCare_backend.repository;

import com.ApeCare_backend.entity.Marca;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MarcaRepository extends JpaRepository<Marca, Long> {
    List<Marca> findByEstadoNombreIgnoreCase(String estado);
}